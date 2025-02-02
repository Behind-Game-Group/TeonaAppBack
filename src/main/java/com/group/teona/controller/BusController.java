package com.group.teona.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.entities.Bus;
import com.group.teona.entities.City;
import com.group.teona.form.FormBus;
import com.group.teona.repositories.UserRepository;
import com.group.teona.services.BusService;
import com.group.teona.services.CityService;

@RestController
@RequestMapping("api/add")
public class BusController {
	
	@Autowired
	private BusService busService;
	
	 
		@PostMapping("bus")
	    public ResponseEntity addBus ( @RequestBody FormBus bus  ) {
				    				    	
		return ResponseEntity.ok(busService.addBus(bus));


	    }
		

	
	
}
