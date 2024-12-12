package com.group.teona.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	

	
	@Column(name = "cardTitle", nullable = false)
	    private String cardTitle;
	    
	@Column(name = "cardPrice", nullable = false)
	    private Double cardPrice;
	
	@Column(name = "isActive", nullable = false)
	    private Boolean isActive;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
	
	@ManyToOne
	@JoinColumn(name = "wallet_id", nullable = true)
	private Wallet wallet;
	
	@ManyToOne
	@JoinColumn(name = "adress_id", nullable = true)
	private Adress adress;
	

}
