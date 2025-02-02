package com.group.teona.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.entities.Journey;
import com.group.teona.entities.User;
import com.group.teona.form.FormAddJourney;
import com.group.teona.form.FormAddReservation;
import com.group.teona.repositories.JourneyRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.services.JourneyService;
import com.group.teona.services.ReservationService;

@RestController
@RequestMapping("api/add")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JourneyRepository journeyRepository;
	
	@PostMapping("/reservation")
	public ResponseEntity addReservation (Authentication authentication, @RequestParam Long journeyID, @RequestBody FormAddReservation formAddReservation) {
    	
		if(authentication != null) {
	    	
	    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
	    	Optional<Journey> journeyFind = journeyRepository.findById(journeyID);
	    	
	    	if(userFind.isPresent()) {
	    		
	    		return ResponseEntity.ok(reservationService.addReservation(userFind.get(), journeyFind.get(), formAddReservation));

	    		}
	    	
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist");

    		
    		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Auth fail");
	}

}
