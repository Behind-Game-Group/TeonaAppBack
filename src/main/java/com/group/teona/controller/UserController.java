package com.group.teona.controller;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;




import com.group.teona.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.group.teona.dto.LoginRequest;
import com.group.teona.dto.SignUpRequest;
import com.group.teona.dto.VerifyRequest;
import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.services.UserService;
import com.group.teona.services.EmailService;



@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private  JwtService jwtService;
	@Autowired
	private  EmailService emailService;


	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> signUp(@RequestBody SignUpRequest request) {
		 User user = request.getUser();
		 if(request.getAdress() == null) {System.out.println("adresses nulles");}
	        Set<Adress> adresses = new HashSet<>(request.getAdress());
	        if (userService.emailExists(user.getEmail())) {
	        	Map<String, String> response = new HashMap<>();
	            response.put("status", "error");
	            response.put("message", "Email already exists");
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	        }
	        String verificationCode = String.format("%06d", new Random().nextInt(999999));
	        user.setVerificationCode(verificationCode);
	        user.setCodeExpirationTime(LocalDateTime.now().plusMinutes(10));
	        user.setVerified(false);

	    
	        userService.signUp(user, adresses);

	   
	        boolean emailSent = emailService.sendVerificationEmail(user.getEmail(), verificationCode);
	        if (!emailSent) {
	        	Map<String, String> response = new HashMap<>();
	            response.put("status", "error");
	            response.put("message", "Failed to send verification email. Please try again.");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(response);
	        }

	        return ResponseEntity.status(HttpStatus.FOUND)
	                .header(HttpHeaders.LOCATION, "/verify")
	                .build();

	}
	
	@PostMapping("/verify")
	public ResponseEntity<String> verifyCode(@RequestBody VerifyRequest request) {
	   
	    User user = userService.findByEmail(request.getEmail());
	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
	    }

	   
	    if (!user.getVerificationCode().equals(request.getCode())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code.");
	    }

	    if (user.getCodeExpirationTime().isBefore(LocalDateTime.now())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification code has expired.");
	    }

	
	    user.setVerified(true);
	    user.setVerificationCode(null);
	    user.setCodeExpirationTime(null);
	    userService.updateUser(user);

	    return ResponseEntity.ok("Votre compte a été vérifié avec succès.");
	}
	

	@GetMapping("/test")
	@PreAuthorize("hasAuthority('User')")
	public  ResponseEntity<String> testJwt(){
		return ResponseEntity.ok("nice code");
	}



	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String,String> req) {
		  String email = req.get("email");
		    String pass = req.get("pass");
		    if (email == null || pass == null) {
		        return ResponseEntity.badRequest().body("Email and password must not be null");
		    }

		    try {
		        // Attempt login
		        Optional<User> user = userService.login(email, pass);
		        if (user.isPresent()) {
		            String jwt = jwtService.generateToken(user.get());
		            return ResponseEntity.ok("Login successful. Token: " + jwt);
		        } else {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
		        }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		    }



	}
}

