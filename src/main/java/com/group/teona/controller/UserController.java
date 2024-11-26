package com.group.teona.controller;

import java.util.*;


import com.group.teona.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.group.teona.dto.LoginRequest;
import com.group.teona.dto.SignUpRequest;
import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.services.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private  JwtService jwtService;


	@PostMapping("/register")
	public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
		 User user = request.getUser();
		 if(request.getAdress() == null) {System.out.println("adresses nulles");}
	        Set<Adress> adresses = new HashSet<>(request.getAdress());
	        if (userService.emailExists(user.getEmail())) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
	        }
	        // Delegate to the service layer
	        userService.signUp(user, adresses);


		return ResponseEntity.ok("utilisateur enregistré avec succès");
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
//		System.out.println(req);
//		Optional<User> user=userService.login(req.get("email"),req.get("pass"));
//		if (user.isPresent()){
//		String jwt = jwtService.generateToken(user.get());
//		return ResponseEntity.ok("vous vous êtes conecter avec succes + token "+jwt);}
//		return ResponseEntity.ofNullable("user or password is incorrect");
	}


}
