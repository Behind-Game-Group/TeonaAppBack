 
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "count", nullable = false)
	private double count;
    
    @Column(name = "phoneNumber", nullable = false, unique = true)
    private String phoneNumber;
	
    
	@OneToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;
	
	
	@OneToOne(mappedBy = "wallet", cascade = CascadeType.ALL)
	private Pass pass;
	
	@OneToMany( mappedBy = "wallet", cascade = CascadeType.ALL)
	Set<Card> cards = new HashSet<>();

	@OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
	private Set<VisaCard> visacards = new HashSet<>();
	
    public void addFunds(double amount) {
        this.count += amount;
    }

    public boolean deductFunds(double amount) {
        if (amount > this.count) {
            return false; 
        }
        this.count -= amount;
        return true;
    }
    
    @Override
    public int hashCode() {
    	return Objects.hashCode(id);
    	    }
    
    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id + ", " +
                "phoneNumber='" + phoneNumber + "', " +
                "count=" + count + ", " +
                "userId=" + (user != null ? user.getId() : "None") + ", " +
                "cardsSize=" + cards.size() + ", " +  
                "visacardsSize=" + visacards.size() + "}";
    }
    
}

