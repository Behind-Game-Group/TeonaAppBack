package com.group.teona.entities;

import io.jsonwebtoken.lang.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Card {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "topUp", nullable = false)
	private double topUp;
	
	
	@Column(name = "isActive", nullable = false)
    private boolean isActive;
	
	@ManyToOne
	@JoinColumn(name = "wallet_id", nullable = true)
	private Wallet wallet;
	
	@ManyToOne
	@JoinColumn(name = "adress_id", nullable = true)
	private Adress adress;
	
	 @Override
	    public int hashCode() {
	    	return Objects.hashCode(id);
	    	    }

}