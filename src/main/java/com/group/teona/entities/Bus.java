package com.group.teona.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name = "bus")
@Getter
@Setter
public class Bus implements Serializable {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  
	    @Column(nullable = false, unique = true)
	    private String numbers;
	    
	    @Column(nullable = true)
		@OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
	    private List<Seat> seats;
	    
	    @Column(nullable = true)
		@OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
	    private List<Journey> journeys;
	    
	  	

}
