package com.group.teona.form;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FormParamJourney {
	
	private String CityDeparture;
	
	private String CityArrival;
	
	private LocalDate dateDepart;

}
