package com.group.teona.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.entities.Journey;
import com.group.teona.entities.User;
import com.group.teona.form.FormAddJourney;
import com.group.teona.form.FormAddReservation;
import com.group.teona.form.FormParamJourney;
import com.group.teona.repositories.JourneyRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.services.JourneyService;

@RestController
@RequestMapping("api/add/journey")
public class JourneyController {
	
	@Autowired
	private JourneyService journeyService;
	
	@Autowired
	private JourneyRepository journeyRepository;
	
	@PostMapping("/add")
	public ResponseEntity addJourney (@RequestBody FormAddJourney journey) {
    	
		return ResponseEntity.ok(journeyService.addJourney(journey));

	}
	
	
	@GetMapping("/get")
	public ResponseEntity getJourneys (@RequestParam String CityDeparture, @RequestParam String CityArrival, 
			@RequestParam LocalDate dateDepart ) {
		
		
		return ResponseEntity.ok(journeyService.getJourney(CityDeparture, 
														   CityArrival,
														   dateDepart));

	}
	
	@GetMapping("/getID")
	public ResponseEntity getJourneyDate (@RequestParam Long JourneyID) {
		
		Optional<Journey> journey = journeyRepository.findById(JourneyID);
		return ResponseEntity.ok(journey.get());
	}
	

	
	
}
