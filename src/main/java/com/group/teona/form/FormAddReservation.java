package com.group.teona.form;

import java.time.LocalDate;

import com.group.teona.entities.City;
import com.group.teona.entities.Seat;

 import lombok.Data;


@Data
public class FormAddReservation {
	
	private LocalDate date;
	
	private String cityDeparture;
	
	private String cityArrival;
	
	private Seat seat;

	private boolean adult;
	
	private boolean children;
	
	private boolean withBike;

}
