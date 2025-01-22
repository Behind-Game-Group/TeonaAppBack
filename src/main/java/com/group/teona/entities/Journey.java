package com.group.teona.entities;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Journey {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToMany
	 @JoinTable(
		 name = "journey_city", 
		 joinColumns = { @JoinColumn(name = "journey_id") }, 
		 inverseJoinColumns = { @JoinColumn(name = "city_id") }
		     )
	@JsonBackReference
	private List<City> cities = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "cityArrival_id")
	private Bus bus;
	
	@OneToOne
	@JoinColumn(name = "seat_id")
	private Seat seat;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dateDepart;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dateArrival;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Duration duration;
	
	@Column(nullable = true)
	private boolean adult;
	
	@Column(nullable = true)
	private boolean children;
	
	@Column(nullable = false)
	private double priceAdult;
	
	@Column(nullable = false)
	private double priceChildren;
	
	@Column(nullable = true)
	private boolean withBike;
	
	@ManyToMany
	 @JoinTable(
	 name = "User_Journey", 
	 joinColumns = { @JoinColumn(name = "user_id") }, 
	 inverseJoinColumns = { @JoinColumn(name = "journet_id") })
    @JsonBackReference
	private Set <User> user;
	
	
}