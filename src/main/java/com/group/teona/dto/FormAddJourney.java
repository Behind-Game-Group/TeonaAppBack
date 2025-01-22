package com.group.teona.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class FormAddJourney {
	
	private List<String> cities;
	
	private String bus;
	
	private LocalDateTime dateDepart;

	private LocalDateTime dateArrival;
	
	private double priceAdult;
	
	private double priceChildren;



}
