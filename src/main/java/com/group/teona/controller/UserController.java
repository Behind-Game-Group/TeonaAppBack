package com.group.teona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.entities.User;
import com.group.teona.services.UserService;


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/user/signup")
	public ResponseEntity<String> signUp(@RequestBody User user) {
		userService.signUp(user);
		return ResponseEntity.ok("utilisateur enregistré avec succès");
	}

}
