package com.group.teona.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.group.teona.entities.Station;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class GetJourney {
	
	private Long id;

    private String busNumber;

    private LocalDateTime dateDepart;
	
	private LocalDateTime dateArrival;
	
	private Duration duration;
	
	private double price;
	
    private List<Station> stations = new ArrayList<>(); 
    
    private List<GetStation> getStations = new ArrayList<>(); 

    

}
