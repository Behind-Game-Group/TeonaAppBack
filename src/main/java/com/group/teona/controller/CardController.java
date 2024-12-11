package com.group.teona.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.dto.FormTeonaCard;
import com.group.teona.entities.User;
import com.group.teona.repositories.UserRepository;
import com.group.teona.services.CardService;

@RestController
@RequestMapping("api/add")
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	 @Autowired
	private UserRepository userRepository;
/*
	@PostMapping("card/user")
    public ResponseEntity saveFormCardWithUser(Authentication authentication, @RequestBody FormTeonaCard formRequest) {
		
	    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
	    	
	    	if(userFind.isPresent()) {
	    	
		    	cardService.saveFormCardWithUser(formRequest, userFind.get());
		    	
		    	return ResponseEntity.ok("Your card has been created successfully " + userFind.get().getFirstName() );
	    	}
	    	
	        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist");
		}
		

	@PostMapping("card/only")
	public ResponseEntity saveFormCardWithoutUser( @RequestBody FormTeonaCard formRequest) {
		
		cardService.saveFormCardWithoutUser(formRequest);
		return ResponseEntity.ok("Your card has been created successfully");
	}
	*/
	 
		@PostMapping("card")
	    public ResponseEntity saveFormWithCard(Authentication authentication, @RequestBody FormTeonaCard formRequest) {
			
			if(authentication != null) {
	    	
		    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
		    	
		    	if(userFind.isPresent()) {
		    	
			    	cardService.saveFormCardWithUser(formRequest, userFind.get());
			    	
			    	return ResponseEntity.ok("Your card has been created successfully " + userFind.get().getFirstName() );
		    	}
		    	
		        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist");
			}
			cardService.saveFormCardWithoutUser(formRequest);
			return ResponseEntity.ok("Your card has been created successfully ");

	    }
	
}
