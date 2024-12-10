package com.group.teona.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.repositories.UserRepository;
import com.group.teona.security.JwtService;
import com.group.teona.services.AdressService;
import com.group.teona.dto.FormAdress;
import com.group.teona.dto.FormTeonaPass;

@RestController
@RequestMapping("/api/add")
public class AdressController {
    
    @Autowired
	private AdressService adressService;
	 
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private JwtService jwtService;
    
    
    @PostMapping("adress")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> saveForm(@RequestBody FormAdress formRequest, 
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader ) {
        try {
        	
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
                String token = authorizationHeader.substring(7);

             
                if (jwtService.isTokenValid(token)) {
                    String username = jwtService.extractUsername(token);
                    User user = userRepository.findByEmail(username)
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));

                    // Save the address with the associated user
                    adressService.saveFormWithUser(formRequest, user);
                    return ResponseEntity.status(HttpStatus.OK).body("Address saved with user association");
                } 
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Authentication failed ");
            }
                	/*                }
                    // Invalid token but saving without a user
                    adressService.saveFormWithoutUser(formRequest);
                    return ResponseEntity.status(HttpStatus.OK).body("Address saved without user association");
                }
                                
//            if (formRequest.getUserId() != null) {
//                Optional<User> userOptional = userRepository.findById(formRequest.getUserId());
//                if (userOptional.isEmpty()) {
//                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//                }
//
//                User user = userOptional.get();
//            
//                adressService.saveFormWithUser(formRequest, user);
//            } else {
//                
//            	adressService.saveFormWithoutUser(formRequest);
//            }
//
//            return ResponseEntity.ok("Form saved successfully");
 * 
 */
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred: " + e.getMessage());
        }
		return null;
    }
    
    @PostMapping("adressCard")
    public ResponseEntity saveFormWithCard(@RequestParam Long walletId, @RequestBody FormAdress formRequest) {
    	
    	adressService.saveFormWithCard(walletId, formRequest);
    	
    	return ResponseEntity.ok("Carte ajoutée avec succès");

    }
        
    }


    
    
 



