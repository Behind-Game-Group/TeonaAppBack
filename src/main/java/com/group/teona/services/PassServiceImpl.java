package com.group.teona.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.dto.PassRequestDto;
import com.group.teona.entities.Adress;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.PassRepository;
import com.group.teona.repositories.UserRepository;

@Service
public class PassServiceImpl implements PassService {
	  @Autowired
	    private PassRepository passRepository;
	  
	  @Autowired
	    private AdressRepository adressRepository;

	  
	    public void savePass(PassRequestDto passRequest, User user,Long adressId,Wallet wallet) {
	    	 if (passRequest == null || user == null || adressId == null || wallet == null) {
	             throw new IllegalArgumentException("Invalid input: PassRequest, User, Address ID, or Wallet is null");
	         }
	    	  Optional<Adress> optionalAdress = adressRepository.findById(adressId); 

	          if (optionalAdress.isEmpty()) {
	              throw new IllegalArgumentException("Address with ID " + adressId + " not found");
	          }

	          Adress adress = optionalAdress.get();
	          
	          String cardTitle = passRequest.getCardTitle();
	          int validityDuration = getValidityDuration(cardTitle);	      
	          

	        Pass pass = new Pass();
	        pass.setCardTitle(passRequest.getCardTitle());
	        pass.setDateSubscription(LocalDate.now());
	        pass.setCardPrice(passRequest.getCardPrice());
	        pass.setIsActive(passRequest.isActive());
	        pass.setUser(user);
	        pass.setAdress(adress);
	        pass.setWallet(wallet);
	        pass.setValidityDuration(validityDuration);
	        LocalDate expirationDate = LocalDate.now().plusDays(validityDuration);
	          pass.setExpirationDate(expirationDate);; 

	        passRepository.save(pass);
	    }
	    
	    private int getValidityDuration(String cardTitle) {
	        switch (cardTitle) {
	            case "TeonaPass Yearly Pass":
	                return 365; 
	            case "TeonaPass Monthly Pass":
	                return 30; 
	            case "TeonaPass Weekly Pass":
	                return 7;
	            case "TeonaPass Daily Pass":
	                return 1;
	            default:
	                throw new IllegalArgumentException("Invalid card title: " + cardTitle);
	        }
	    }

	
}