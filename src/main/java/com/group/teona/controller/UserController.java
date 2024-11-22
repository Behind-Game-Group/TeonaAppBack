package com.group.teona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.group.teona.register.UserRegister;
import com.group.teona.services.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> signUp(@RequestBody UserRegister userRegister ) {
		
		userService.signUp(userRegister.getUser(), userRegister.getAdresses());
		return ResponseEntity.ok("utilisateur enregistré avec succès");
	}

}
