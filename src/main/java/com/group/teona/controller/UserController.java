package com.group.teona.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.group.teona.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
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
	        Set<Adress> adresses = new HashSet<>(request.getAdress());

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
	public ResponseEntity<String> login(@RequestBody Map<String,String> req) {
		System.out.println(req);
		Optional<User> user=userService.login(req.get("email"),req.get("pass"));
		if (user.isPresent()){
		String jwt = jwtService.generateToken(user.get());
		return ResponseEntity.ok("vous vous êtes conecter avec succes + token "+jwt);}
		return ResponseEntity.ofNullable("user or password is incorrect");
	}


}
