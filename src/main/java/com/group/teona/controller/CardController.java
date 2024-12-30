package com.group.teona.controller;

import java.util.Optional;

import com.group.teona.dto.FromTopUpDTO;
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

import com.group.teona.dto.FormTeonaCard;
import com.group.teona.dto.FormTopUp;
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
	 
		@PostMapping("card")
	    public ResponseEntity saveFormWithCard(Authentication authentication, @RequestBody FormTeonaCard formRequest) {
			
			if(authentication != null) {
	    	
		    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
		    	
		    	if(userFind.isPresent()) {
		    				    	
			    	return ResponseEntity.ok(cardService.saveFormCardWithUser(formRequest, userFind.get()));
		    	}
		    	
		        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist");
			}
					return ResponseEntity.ok(cardService.saveFormCardWithoutUser(formRequest));

	    }
		

		@PostMapping("card/topUp")
	    public ResponseEntity saveFormWithCard( @RequestBody FromTopUpDTO defaul) {
			try {
				FormTopUp topUp=defaul.getDefaut();
				System.out.println(defaul);
//				topUp.setCardId(102l);topUp.setTopUp10(true);
				cardService.addTopUp(topUp.getCardId(), topUp);
				return ResponseEntity.ok("Top-up added");
				
			} catch (Exception e) {
	        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Add a valid top-up"+e);
			}
			
		
		}
	
	
}
