package com.group.teona.services;


import org.springframework.stereotype.Service;

import com.group.teona.entities.Journey;
import com.group.teona.entities.Reservation;
import com.group.teona.entities.User;
import com.group.teona.form.FormAddReservation;



@Service
public interface ReservationService {
	
	public Reservation addReservation (User user, Journey journey, FormAddReservation formAddReservation );
	
}
