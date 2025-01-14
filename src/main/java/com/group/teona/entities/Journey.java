package com.group.teona.entities;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	
	@ManyToOne
    @JoinColumn(name = "cityDepart_id") 
	private City cityDepart;
	
	@ManyToOne
	@JoinColumn(name = "cityArrival_id")
	private City cityArrival;
	
	@OneToOne
	@JoinColumn(name = "seat_id")
	private Seat seat;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate dateDepart;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate dateArrival;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Duration duration;
	
	@Column(nullable = false)
	private boolean withBike;
	
	@Column(nullable = false)
	private double price;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    @JsonBackReference
	private User user;
	
	
}
