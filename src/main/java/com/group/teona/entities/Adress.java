 package com.group.teona.entities;


import java.io.Serializable;
import java.sql.Blob;
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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"cards", "pass", "user"})
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
	    
	    
		 @Column(nullable = true)
		private String image;

	    @Column(nullable = false)
	    private String postCode;

	    @Column(nullable = false)
	    private String city;

	    @Column(nullable = true, unique = false)
	    private String countryCode;

	    @Column(nullable = false)
	    private String country;

		
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = true) 
	    @JsonBackReference
	    private User user;
	        
	    @OneToMany(mappedBy = "adress", cascade = CascadeType.ALL)
	    @JsonBackReference
		private Set<Card> cards = new HashSet<>();
	    
	    @OneToMany(mappedBy = "adress", cascade = CascadeType.ALL)
	    @JsonBackReference
		private Set<Pass> pass = new HashSet<>();
	    
	    @Override
	    public int hashCode() {
	    	return Objects.hashCode(id);
	    	    }

	    @Override
	    public String toString() {
	        return "Adress{" +
	                "id=" + id +
	                ", firstName='" + firstName + '\'' +
	                ", lastName='" + lastName + '\'' +
	                ", streetName='" + streetName + '\'' +
	                ", streetNameOptional='" + streetNameOptional + '\'' +
	                ", image='" + image + '\'' +
	                ", postCode='" + postCode + '\'' +
	                ", city='" + city + '\'' +
	                ", countryCode='" + countryCode + '\'' +
	                ", country='" + country + '\'' +
	                ", userId=" + (user != null ? user.getId() : "null") +  
	                '}';
	    }
}
