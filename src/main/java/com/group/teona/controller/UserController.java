package com.group.teona.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import com.group.teona.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.group.teona.dto.LoginRequest;
import com.group.teona.dto.SignUpRequest;
import com.group.teona.dto.VerifyRequest;
import com.group.teona.dto.forgotPasswordRequest;
import com.group.teona.dto.resetPasswordRequest;
import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.repositories.UserRepository;
import com.group.teona.services.UserService;
import com.group.teona.services.EmailService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private EmailService emailService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<Map<String, String>> signUp(@RequestBody SignUpRequest request) {
		User user = request.getUser();

		if (request == null || request.getUser() == null) {
			Map<String, String> response = new HashMap<>();
			response.put("status", "error");
			response.put("message", "User data is missing.");
			return ResponseEntity.badRequest().body(response);
		}

		String verificationCode = String.format("%06d", new Random().nextInt(999999));
		user.setVerificationCode(verificationCode);
		user.setCodeExpirationTime(LocalDateTime.now().plusMinutes(10));
		user.setVerified(false);

		userService.signUp(user);

		boolean emailSent = emailService.sendVerificationEmail(user.getEmail(), verificationCode);
		if (!emailSent) {
			Map<String, String> response = new HashMap<>();
			response.put("status", "error");
			response.put("message", "Failed to send verification email. Please try again.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

		Map<String, String> response = new HashMap<>();
		response.put("status", "success");
		response.put("message", "User registered successfully. Please verify your email.");
		response.put("redirectUrl", "/api/user/verify");

		return ResponseEntity.ok(response);
	}

	@PostMapping("/verify")
	public ResponseEntity<String> verifyCode(@RequestBody VerifyRequest request) {

		User user = userService.findByEmail(request.getEmail());

		if (!user.getVerificationCode().equals(request.getCode())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code.");
		}

		if (user.getCodeExpirationTime().isBefore(LocalDateTime.now())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification code has expired.");
		}

		user.setVerified(true);
		userService.updateUser(user);
		return ResponseEntity.ok("Votre compte a été vérifié avec succès.");
	}

	@GetMapping("/test")
	@PreAuthorize("hasAuthority('User')")
	public ResponseEntity<String> testJwt() {
		return ResponseEntity.ok("nice code");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest req) {
		String email = req.getEmail();
		String password = req.getPassword();

		if (email == null || password == null) {
			return ResponseEntity.badRequest().body("Email and password must not be null");
		}

		try {
			// Attempt login
			Optional<User> user = userService.login(email, password);
			if (user.isPresent()) {
				User loggedInUser = user.get();
				String jwt = jwtService.generateToken(loggedInUser, loggedInUser.getEmail(), loggedInUser.getId());

				// Return both jwt and userId in a Map
				Map<String, Object> response = new HashMap<>();
				response.put("jwt", jwt);
				response.put("userId", loggedInUser.getId());

				return ResponseEntity.ok(response);

			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody forgotPasswordRequest request) {

		String email = request.getEmail();

		if (email == null || email.isEmpty()) {
			return ResponseEntity.badRequest().body("Email is required.");
		}

		try {
			User user = userService.findByEmail(email);
			String resetToken = UUID.randomUUID().toString();
			user.setResetToken(resetToken);
			user.setTokenExpirationTime(LocalDateTime.now().plusMinutes(30));
			userService.updateUser(user);

			// Send email with reset token
			emailService.sendPasswordResetEmail(user.getEmail(), resetToken);

			return ResponseEntity.ok("Password reset email sent successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while processing the request.");
		}
	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody resetPasswordRequest request) {
		String resetToken = request.getToken();
		String newPassword = request.getNewPassword();

		if (resetToken == null || resetToken.isEmpty() || newPassword == null || newPassword.isEmpty()) {
			return ResponseEntity.badRequest().body("Token and new password are required.");
		}

		try {
			User user = userService.findByResetToken(resetToken);

			if (user.getTokenExpirationTime() == null || user.getTokenExpirationTime().isBefore(LocalDateTime.now())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reset token has expired.");
			}

			// Update the password
			user.setPassword(passwordEncoder.encode(newPassword));
			user.setResetToken(null);
			user.setTokenExpirationTime(null);
			userService.updateUser(user);

			return ResponseEntity.ok("Password has been reset successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while resetting the password.");
		}
	}
}
