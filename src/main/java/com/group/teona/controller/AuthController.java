package com.group.teona.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group.teona.dto.LoginRequest;
import com.group.teona.services.UserService;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private UserService userService;

    @GetMapping("test")
    public ResponseEntity tested (){
        return ResponseEntity.ok("it a test");
        
        
    }
    
    @PostMapping("login")
	public ResponseEntity logIn (@RequestBody LoginRequest loginRequest) {
    	userService.logIn(loginRequest);
    	
		return ResponseEntity.ok("Vous etes connecté !");

    }

}

