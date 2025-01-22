package com.group.teona.dto;

import com.group.teona.entities.Seat;

 import lombok.Data;


@Data
public class FormAddUserJourney {
	
	private Seat seat;

	private boolean adult;
	
	private boolean children;
	
	private boolean withBike;

}
