package com.group.teona.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.group.teona.enums.EnumRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private LocalDate date;
	
	@Column(nullable = true)
	private boolean adult;
	
	@Column(nullable = true)
	private boolean children;
	
	@Column(nullable = true)
	private boolean withBike; 
	
	@Column(nullable = true)
	private double finalPrice;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") 
	private User user;
	
	@ManyToMany
	 @JoinTable(
	 name = "Reservation_Station", 
	 joinColumns = { @JoinColumn(name = "reservation_id") }, 
	 inverseJoinColumns = { @JoinColumn(name = "station_id") })
	private List <Station> stations = new ArrayList<>();
	// Contient la station de départ et celle d'arrivée
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journey_id") 
	private Journey journey;
}
