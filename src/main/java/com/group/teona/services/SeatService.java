package com.group.teona.services;


import org.springframework.stereotype.Service;

import com.group.teona.dto.FormSeat;
import com.group.teona.entities.Seat;



@Service
public interface SeatService {
	Seat addSeat (FormSeat formSeat);
}
