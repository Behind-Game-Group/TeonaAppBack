package com.group.teona.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


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
	    private UserDetailsService userDetailsService;
	
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
		            String usernameFromToken = jwtService.extractUsername(token);
	                String emailFromToken = jwtService.extractEmail(token);

	                
	                UserDetails userDetails = userDetailsService.loadUserByUsername(usernameFromToken);

	                
		            if (jwtService.isTokenValid(token, userDetails, emailFromToken)) {		                
		            	 User user = userRepository.findByEmail(usernameFromToken)
		                         .orElseThrow(() -> new IllegalArgumentException("User not found"));

		            	 
		                Long adressId = passRequest.getAdressId();
		                
		                Wallet wallet;
		                if (passRequest.getWalletId() != null) {
		                    wallet = walletRepository.findById(passRequest.getWalletId())
		                            .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
		                } else {
		                	Double cardPrice = passRequest.getCardPrice();
		                	if (cardPrice == null || cardPrice < 0) {
		                	    throw new IllegalArgumentException("Card price must be a positive value.");
		                	}
		                	  wallet = new Wallet();
		                	wallet.setCount(cardPrice);
		                    wallet = new Wallet();
		                    wallet.setUser(user);
		                    wallet.setCount(cardPrice); 
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
