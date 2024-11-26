package com.group.teona.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.dto.SignUpRequest;
import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.services.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/register")

	public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
		 User user = request.getUser();
	        Set<Adress> adresses = new HashSet<>(request.getAdress());

	        // Delegate to the service layer
	        userService.signUp(user, adresses);


		return ResponseEntity.ok("utilisateur enregistré avec succès");
	}

}
