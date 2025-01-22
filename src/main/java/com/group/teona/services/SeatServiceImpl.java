package com.group.teona.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.dto.FormSeat;
import com.group.teona.entities.Bus;
import com.group.teona.entities.Seat;
import com.group.teona.repositories.BusRepository;
import com.group.teona.repositories.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService {
	
	@Autowired
    SeatRepository seatRepository;
	
	@Autowired
    BusRepository busRepository;
	
	public Seat addSeat (FormSeat formSeat) {
		
		Seat seat = new Seat();
		Bus bus = busRepository.findByNumbers(formSeat.getBus());
		seat.setNumber(formSeat.getNumber());
		seat.setBus(bus);
		 return seatRepository.save(seat);
	}

}
