package com.group.teona.entities;

import java.sql.Blob;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group.teona.enums.EnumSub;

import io.jsonwebtoken.lang.Objects;
import jakarta.persistence.*;
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
	    


      @Lob
	 @Column(nullable = true, columnDefinition = "LONGBLOB")
	private Blob image;

    @Column(name = "subscription_time", nullable = true)
	@Enumerated(EnumType.STRING)
    private EnumSub subscriptionTime;
    

    
    @Column(name = "date_subscription")
	@Temporal(TemporalType.DATE)
	private LocalDate dateSubscription;
    
    @Column(name = "cardTitle", nullable = true)
    private String cardTitle;
    
	@Column(name = "cardPrice", nullable = true)
	private Double cardPrice;

    @Column(name = "isActive", nullable = true)
    private boolean isActive;
    
   @OneToOne
   @JoinColumn(name = "wallet_id", nullable = true)
   @JsonBackReference
	private Wallet wallet;
   
   @OneToOne
   @JoinColumn(name = "user_id", nullable = false)
   @JsonIgnore
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name = "adress_id", nullable = true)
	private Adress adress;
	
	@Column(name = "validity_duration", nullable = true)
    private Integer validityDuration;

    @Column(name = "expiration_date", nullable = true)
    private LocalDate expirationDate ;
    
    @Override
    public int hashCode() {
    	return Objects.hashCode(id);
    	    }




}
