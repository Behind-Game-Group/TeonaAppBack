package com.group.teona.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adress implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String firstName;

	    @Column(nullable = false)
	    private String lastName;

	    @Column(nullable = false)
	    private String streetName;

	    private String streetNameOptional;

	    @Column(nullable = false)
	    private String postCode;

	    @Column(nullable = false)
	    private String city;

	    @Column(nullable = false)
	    private String phoneNumber;

	    @Column(nullable = false)
	    private String country;

	    private String image;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = true) 
	    private User user;
	    
	    @OneToOne(mappedBy = "adress", cascade = CascadeType.ALL)
		private Card card;

}
