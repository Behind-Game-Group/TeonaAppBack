package com.group.teona.controller;



import java.util.HashMap;
import java.util.Map;

import com.group.teona.entities.Adress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.PassRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.repositories.WalletRepository;
import com.group.teona.security.JwtService;
import com.group.teona.services.PassService;

import com.group.teona.dto.PassRequestDto;

@RestController
@RequestMapping("/api/add")
public class PassController {

	

	@Autowired
	PassService passService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AdressRepository adressRepository;
	
	@Autowired
	PassRepository passRepository;
	
	@Autowired
	WalletRepository walletRepository;

	@Autowired
	private JwtService jwtService;
	

	@PostMapping("/savePass")
	@CrossOrigin(origins = "http://localhost:8081")
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

	
		                passService.savePass(passRequest, user,adressId);
		             
		                Map<String, Object> response = new HashMap<>();
		                response.put("message", "Pass saved successfully");
		                response.put("cardPrice",user.getWallet().getCount());

		                return ResponseEntity.ok(response);
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