package com.group.teona.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.security.JwtService;
import com.group.teona.services.AdressService;
import com.group.teona.services.PassService;
import com.group.teona.dto.FormAdress;
import com.group.teona.dto.FormTeonaPass;
import com.group.teona.dto.PassRequestDto;

@RestController
@RequestMapping("/api/add")
public class AdressController {

	@Autowired
	private AdressService adressService;

	@Autowired
	private PassService passService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdressRepository adressRepository;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/saveAddress")
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<?> saveForm(@RequestBody FormTeonaPass formRequest,
			@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
		 try {
		        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
		            String token = authorizationHeader.substring(7);

		            if (jwtService.isTokenValid(token)) {
		                String username = jwtService.extractUsername(token);
		                User user = userRepository.findByEmail(username)
		                        .orElseThrow(() -> new IllegalArgumentException("User not found"));

		                Adress savedAddress= adressService.saveAddress(formRequest, user);
		                return ResponseEntity.ok(Map.of(
		                        "message", "Address saved successfully",
		                        "id", savedAddress.getId() 
		                        
		                    ));	                
		                
		            }
		         
		        }
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		    }
	}
	
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

		                passService.savePass(passRequest, user,adressId);
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
//		try {
//            String token = authorizationHeader.substring(7);
//            if (jwtService.isTokenValid(token)) {
//                String username = jwtService.extractUsername(token);
//                User user = userRepository.findByEmail(username)
//                        .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//                passService.savePass(passRequest, user);
//                return ResponseEntity.ok("Pass saved successfully");
//            }
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving pass: " + e.getMessage());
//        }

}
}
