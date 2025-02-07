package com.group.teona.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.jsonwebtoken.lang.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	
	
	@Column(name = "cardPrice", nullable = true)
	private Double cardPrice;
	
	@Column(name = "date_subscription")
	@Temporal(TemporalType.DATE)
	private LocalDate dateSubscription;
	    
	@Column(name = "cardTitle", nullable = true)
    private String cardTitle;
	
	   @Column(name = "paymentStatus", nullable = true)
	    private String paymentStatus;
	   
	@Column(name = "isActive", nullable = false)
    private boolean isActive;
	
	@ManyToOne
	@JoinColumn(name = "wallet_id", nullable = true)
	@JsonIgnore
	private Wallet wallet;
	
	@ManyToOne
	@JoinColumn(name = "adress_id", nullable = true)
	private Adress adress;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;
	
	 @Override
	    public int hashCode() {
	    	return Objects.hashCode(id);
	    	    }

}