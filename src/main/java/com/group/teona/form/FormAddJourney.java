package com.group.teona.form;

import java.time.LocalDateTime;
import java.util.List;

import com.group.teona.entities.Station;

import lombok.Data;

@Data
public class FormAddJourney {
	
	private List<FormAddStation> stations;
	
	private String bus;
	
	private double price;
	
	



}
