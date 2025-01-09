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

	  
	    public void savePass(PassRequestDto passRequest,  User user,Long adressId) {
	    	
	    	 if (passRequest == null || user == null ) {
	             throw new IllegalArgumentException("Invalid input: PassRequest, User, Address ID, or Wallet is null");
	         }
	    	 if (adressId == null && passRequest.getFormTeonaPass() == null ) {
	             throw new IllegalArgumentException("Invalid input: Address is null");
	         }
	    	 String cardTitle = passRequest.getCardTitle();
	          int validityDuration = getValidityDuration(cardTitle);
	    	 
	    	 Pass pass = new Pass();
		        pass.setCardTitle(passRequest.getCardTitle());
		        pass.setCardPrice(passRequest.getCardPrice());
		        pass.setDateSubscription(LocalDate.now());
		        pass.setActive(true);
		        pass.setUser(user);
		        pass.setValidityDuration(validityDuration);
		        LocalDate expirationDate = LocalDate.now().plusDays(validityDuration);
		        pass.setExpirationDate(expirationDate);; 
	    
	    	   
	           if (user.getWallet() != null) {
		    	   Wallet wallet = user.getWallet();
	        	   user.setWallet(wallet);
	        	   pass.setWallet(wallet);
	        	   
	 	          boolean hasActivePass = passRepository.existsByWalletAndIsActive(wallet, true);
	 	          if (hasActivePass) {
	 	              throw new IllegalStateException("User already has an active pass.");
	 	          }
	           }
	           else {	 
	        	   Wallet wallet = new Wallet();
	               wallet.setUser(user);
	               wallet.setPhoneNumber(user.getPhoneNumber());
	               walletRepository.save(wallet);
	        	   pass.setWallet(wallet);


	               // Link wallet to user
	               user.setWallet(wallet);

	 	          boolean hasActivePass = passRepository.existsByWalletAndIsActive(wallet, true);
	 	          if (hasActivePass) {
	 	              throw new IllegalStateException("User already has an active pass.");
	 	          }
	           }
	    	  Optional<Adress> optionalAdress = adressRepository.findById(adressId); 

	          if (optionalAdress.isEmpty()) {
	        	FormTeonaPass formRequest = passRequest.getFormTeonaPass();
	        	Adress adress = new Adress();
	  			adress.setFirstName(formRequest.getFirstName());
	  			adress.setLastName(formRequest.getLastName());
	  			adress.setPhoneNumber(formRequest.getPhoneNumber());
	  			adress.setStreetName(formRequest.getStreetName());
	  			adress.setStreetNameOptional(formRequest.getStreetNameOptional());
	  			adress.setPostCode(formRequest.getPostCode());
	  			adress.setCity(formRequest.getCity());
	  			adress.setCountry(formRequest.getCountry());
	  			adress.setUser(user);
	  			adressRepository.save(adress);
	  			 
	  			pass.setAdress(adress);
	          }
	          else {
		          Adress adress = optionalAdress.get();
		  			adressRepository.save(adress);

		  			pass.setAdress(adress);	        	  
	          }         

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