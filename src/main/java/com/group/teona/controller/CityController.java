package com.group.teona.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.group.teona.entities.City;
import com.group.teona.repositories.UserRepository;
import com.group.teona.services.CityService;

@RestController
@RequestMapping("api/add")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	 @Autowired
	private UserRepository userRepository;
	 
		@PostMapping("city")
	    public ResponseEntity addCity ( @RequestBody City city  ) {
				    				    	
		return ResponseEntity.ok(cityService.addCity(city));


	    }
		

	
	
}
