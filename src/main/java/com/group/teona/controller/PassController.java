package com.group.teona.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.dto.FormTeonaPass;
import com.group.teona.entities.User;
import com.group.teona.repositories.UserRepository;
import com.group.teona.services.PassService;

@RestController
@RequestMapping("api/add")
public class PassController {
	
	@Autowired
	private PassService passService;
	
	 @Autowired
	private UserRepository userRepository;
	
	@PostMapping("pass")
    public ResponseEntity saveFormWithCard(Authentication authentication, @RequestBody FormTeonaPass formRequest) {
    	
    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
    	
    	passService.saveFormPass(formRequest, userFind.get());
    	
    	return ResponseEntity.ok("Pass ajoutée avec succès");

    }

}
