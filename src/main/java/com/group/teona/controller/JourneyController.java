package com.group.teona.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.dto.FormAddJourney;
import com.group.teona.dto.FormAddUserJourney;
import com.group.teona.entities.User;
import com.group.teona.repositories.UserRepository;
import com.group.teona.services.JourneyService;

@RestController
@RequestMapping("api/add")
public class JourneyController {
	
	@Autowired
	private JourneyService journeyService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/journey")
	public ResponseEntity addJourney (@RequestBody FormAddJourney journey) {
    	
		return ResponseEntity.ok(journeyService.addJourney(journey));

	}
	
	@PutMapping("/user/journey")
	public ResponseEntity addJourneyUser (Authentication authentication, @RequestParam Long journeyId,  @RequestBody FormAddUserJourney userJourneyDto) {
    	
		if(authentication != null) {
	    	
		    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
		    	
		    	if(userFind.isPresent()) {
		    		Long userID = userFind.get().getId();
		    		journeyService.addJourneyUser(userID, journeyId, userJourneyDto);
		    		return ResponseEntity.ok("");
		    	}
		    	
	        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist");

	    		
	    	}
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Auth fail");


	}
	
	/*
	 
		@PostMapping("card")
	    public ResponseEntity saveFormWithCard(Authentication authentication, @RequestBody(required = false) FormTeonaCard formRequest,
	    		@RequestParam (required = false) Long adressId ) {
			
			if(authentication != null) {
	    	
		    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
		    	
		    	if(userFind.isPresent()) {
		    				    	
			    	return ResponseEntity.ok(cardService.saveFormCardWithUser(formRequest, userFind.get(), adressId));
		    	}
		    	
		        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist");
			}
					return ResponseEntity.ok(cardService.saveFormCardWithoutUser(formRequest));

	    }
	    
	    */

	
	
}
