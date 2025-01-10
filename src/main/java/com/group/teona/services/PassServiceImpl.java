package com.group.teona.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.group.teona.dto.FormTeonaPass;
import com.group.teona.dto.PassRequestDto;
import com.group.teona.entities.Adress;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.PassRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.repositories.WalletRepository;

@Service
public class PassServiceImpl implements PassService {
	  @Autowired
	    private PassRepository passRepository;
	  
	  @Autowired
	    private AdressRepository adressRepository;
	  
		@Autowired
	    WalletRepository walletRepository;
		
		 @Autowired
		  private UserRepository userRepository;
	  
	    public void savePass(PassRequestDto passRequest,  User user,Long adressId) {
	    	System.out.println("PassRequest: " + passRequest);
	        System.out.println("User: " + user);
	        System.out.println("AdressId: " + adressId);
	    	 if (passRequest == null || user == null || adressId == null ) {
	             throw new IllegalArgumentException("Invalid input: PassRequest, User, Address ID");
	         }
	    
	    	  Optional<Adress> optionalAdress = adressRepository.findById(adressId);

	          if (optionalAdress.isEmpty()) {
	              throw new IllegalArgumentException("Address with ID " + adressId + " not found");
	          }
	          Adress adress = optionalAdress.get();
	    	   
	    	   Wallet wallet = user.getWallet();
	           if (wallet == null) {
	               wallet = new Wallet();
	               wallet.setUser(user);
	               wallet.setPhoneNumber(user.getPhoneNumber());
	               wallet.setCount(passRequest.getCardPrice());
	               walletRepository.save(wallet);

	       	               user.setWallet(wallet);
	           }
//	    	  Optional<Adress> optionalAdress = adressRepository.findById(adressId); 
//
//	          if (optionalAdress.isEmpty()) {
//	              throw new IllegalArgumentException("Address with ID " + adressId + " not found");
//	          }
//
//	          Adress adress = optionalAdress.get();
	          
	          String cardTitle = passRequest.getCardTitle();
	          int validityDuration = getValidityDuration(cardTitle);	      
//	          

	          boolean hasActivePass = passRepository.existsByWalletAndIsActive(wallet, true);
	          if (hasActivePass) {
	        	  List<Pass> activePasses = passRepository.findAllByWalletAndIsActive(wallet, true);
	        	    for (Pass pass : activePasses) {
	        	        if (pass.getExpirationDate().isBefore(LocalDate.now())) {
	        	            pass.setActive(false); 
	        	            passRepository.save(pass);
	        	        }
	        	    }
	        	    
	        	   
	        	    hasActivePass = passRepository.existsByWalletAndIsActive(wallet, true);
	        	    if (hasActivePass) {
	        	        throw new IllegalStateException("User already has an active pass.");
	        	    }
	          }
	          
	        Pass pass = new Pass();
	        pass.setCardTitle(passRequest.getCardTitle());
	        pass.setDateSubscription(LocalDate.now());
	        pass.setCardPrice(passRequest.getCardPrice());
	        pass.setActive(true);
	        pass.setAdress(adress);
	        pass.setWallet(wallet);
	        pass.setUser(user);
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

	    @Scheduled(cron = "0 0 0 * * ?") 
	    public void deactivateExpiredPasses() {
	        List<Pass> expiredPasses = passRepository.findAllByExpirationDateBeforeAndIsActiveTrue(LocalDate.now());
	        for (Pass pass : expiredPasses) {
	            pass.setActive(false);
	        }
	        passRepository.saveAll(expiredPasses);
	        System.out.println("Deactivated " + expiredPasses.size() + " expired passes");
	    }
}