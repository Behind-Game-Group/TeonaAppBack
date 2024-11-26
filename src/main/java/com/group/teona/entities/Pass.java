package com.group.teona.entities;

import java.util.Date;

import com.group.teona.enums.EnumSubscription;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Pass {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    @Column(name = "subscription_type", nullable = false)
    private EnumSubscription subscriptionType;
    
    @Column(name = "valide_duration", nullable = false)
    private double valideDuration;
    
    @Column(name = "date_subscription")
	@Temporal(TemporalType.DATE)
	private Date dateSubscription;
    
    @Column(name = "isActive", nullable = false)
    private boolean isActive;
    
    @OneToOne
	@JoinColumn(name = "wallet_id", nullable = false)
	private Wallet wallet;


}
