package com.group.teona.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city")
@Getter
@Setter
public class City implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	
	@Column(nullable = false, unique = true)
	private String coordinates;
	
	//@JsonBackReference("bus-cities")
	@ManyToMany
	 @JoinTable(
		 name = "city_bus", 
		 joinColumns = { @JoinColumn(name = "city_id") }, 
		 inverseJoinColumns = { @JoinColumn(name = "bus_id") }
		     )
	private List<Bus> busStop = new ArrayList<>();
	
	//@JsonManagedReference("journeys-cities")
	@ManyToMany(mappedBy = "cities")
    private List<Journey> journeys = new ArrayList<>();
	
	

}