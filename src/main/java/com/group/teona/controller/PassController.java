package com.group.teona.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.repositories.WalletRepository;
import com.group.teona.security.JwtService;
import com.group.teona.services.PassService;

import com.group.teona.dto.PassRequestDto;

@RestController
@RequestMapping("/api/add")
public class PassController {

	

	@Autowired
	private PassService passService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdressRepository adressRepository;
	
	@Autowired
	WalletRepository walletRepository;

	@Autowired
	private JwtService jwtService;

	
	@PostMapping("/savePass")
	public ResponseEntity<?> savePass(@RequestBody PassRequestDto passRequest, @RequestHeader(value = "Authorization") String authorizationHeader) {
		  try {
			  System.out.println("Received Authorization Header: " + authorizationHeader);
		        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
		            String token = authorizationHeader.substring(7); 

		           
		            if (token.split("\\.").length != 3) {
		                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Malformed JWT token");
		            }

		            if (jwtService.isTokenValid(token)) {
		                String username = jwtService.extractUsername(token);
		                User user = userRepository.findByEmail(username)
		                        .orElseThrow(() -> new IllegalArgumentException("User not found"));
		                
		                Long adressId = passRequest.getAdressId();
		                
		                Wallet wallet;
		                if (passRequest.getWalletId() != null) {
		                    wallet = walletRepository.findById(passRequest.getWalletId())
		                            .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
		                } else {
		                    
		                    wallet = new Wallet();
		                    wallet.setUser(user);
		                    wallet.setCount(0.0); 
		                    wallet.setPhoneNumber(user.getPhoneNumber());
		                    walletRepository.save(wallet);
		                } 

		                passService.savePass(passRequest, user,adressId,wallet);
		                return ResponseEntity.ok("Pass saved successfully");
		            } else {
		                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
		            }
		        } else {
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authorization header must start with 'Bearer '");
		        }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving pass: " + e.getMessage());
		    }


}
}
