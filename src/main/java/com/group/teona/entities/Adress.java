 package com.group.teona.entities;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.jsonwebtoken.lang.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

	    @Column(nullable = true, unique = true)
	    private String countryCode;

	    @Column(nullable = false)
	    private String country;

		
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = true) 
	    @JsonBackReference
	    private User user;
	        
	    @OneToMany(mappedBy = "adress", cascade = CascadeType.ALL)
		private Set<Card> cards = new HashSet<>();
	    
	    @OneToMany(mappedBy = "adress", cascade = CascadeType.ALL)
		private Set<Pass> pass = new HashSet<>();
	    
	    @Override
	    public int hashCode() {
	    	return Objects.hashCode(id);
	    	    }

}
