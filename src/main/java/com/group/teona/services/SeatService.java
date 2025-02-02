package com.group.teona.services;


import org.springframework.stereotype.Service;

import com.group.teona.entities.Seat;
import com.group.teona.form.FormSeat;



@Service
public interface SeatService {
	Seat addSeat (FormSeat formSeat);
}
