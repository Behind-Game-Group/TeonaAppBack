package com.group.teona.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.form.FormSeat;
import com.group.teona.services.SeatService;

@RestController
@RequestMapping("api/add")
public class SeatController {
	
	@Autowired
	private SeatService seatService;
	
	 
		@PostMapping("seat")
	    public ResponseEntity addSeat ( @RequestBody FormSeat formSeat  ) {
				    				    	
		return ResponseEntity.ok(seatService.addSeat(formSeat));


	    }
		

	
	
}
