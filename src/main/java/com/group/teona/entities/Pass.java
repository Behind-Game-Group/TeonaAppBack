package com.group.teona.entities;

import java.time.LocalDate;

import com.group.teona.enums.EnumSub;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Pass {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	    
	  @Column(nullable = false)
	  private String firstName;
	  
	  @Column(nullable = false)
	  private String lastName;
	
	 @Column(nullable = false)
	private String image;  

    @Column(name = "subscription_time", nullable = false)
	@Enumerated(EnumType.STRING)
    private EnumSub subscriptionTime;
    
    @Column(name = "valide_duration", nullable = true)
    private double valideDuration;
    
    @Column(name = "date_subscription")
	@Temporal(TemporalType.DATE)
	private LocalDate dateSubscription;
    
    @Column(name = "cardTitle", nullable = false)
    private String cardTitle;
    
	@Column(name = "cardPrice", nullable = false)
	private Double cardPrice;

    @Column(name = "isActive", nullable = true)
    private boolean isActive;
    
   @OneToOne
   @JoinColumn(name = "wallet_id", nullable = false)
	private Wallet wallet;
   /*
   @OneToOne
   @JoinColumn(name = "user_id", nullable = false)
	private User user;
	*/
	
	@ManyToOne
	@JoinColumn(name = "adress_id", nullable = true)
	private Adress adress;
	
	@Column(name = "validity_duration", nullable = false)
    private Integer validityDuration;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate ;
    
    
   



}
