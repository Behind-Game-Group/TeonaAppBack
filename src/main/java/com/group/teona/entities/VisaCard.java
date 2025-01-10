package com.group.teona.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import io.jsonwebtoken.lang.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisaCard implements Serializable{

	 private static final long serialVersionUID = 1L;
	 
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "cardOwner", nullable = false)
	    private String cardOwner;

	    @Column(name = "lastFourDigits", nullable = false, length = 4)
	    private String lastFourDigits;

	    @ManyToOne
	    @JoinColumn(name = "wallet_id", nullable = false)
	    private Wallet wallet;
	 
	 
}
