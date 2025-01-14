package com.group.teona.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.group.teona.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.dto.FormTeonaPass;
import com.group.teona.dto.GetAdress;
import com.group.teona.entities.Adress;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;

@Service
public class AdressServiceImpl implements AdressService {
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    AdressRepository adressRepository;
	
	/*
	@Autowired
    WalletRepository walletRepository;
    */
	
	

	@Override
    public Adress saveOrUpdateAddress(FormTeonaPass formRequest, User user) {
		
		 Optional<Adress> existingAddressOpt = adressRepository.findByUserId(user.getId());
		    if (existingAddressOpt.isPresent()) {
		    	  Adress existingAddress = existingAddressOpt.get();
		          existingAddress.setFirstName(formRequest.getFirstName());
		          existingAddress.setLastName(formRequest.getLastName());
		          existingAddress.setStreetName(formRequest.getStreetName());
		          existingAddress.setStreetNameOptional(formRequest.getStreetNameOptional());
		          existingAddress.setPostCode(formRequest.getPostCode());
		          existingAddress.setCity(formRequest.getCity());
		          existingAddress.setCountryCode(formRequest.getCountryCode());
		          existingAddress.setCountry(formRequest.getCountry());

		          return adressRepository.save(existingAddress);
		    }else {
		    
        Adress address = new Adress();
        address.setFirstName(formRequest.getFirstName());
        address.setLastName(formRequest.getLastName());
        address.setStreetName(formRequest.getStreetName());
        address.setStreetNameOptional(formRequest.getStreetNameOptional());
        address.setPostCode(formRequest.getPostCode());
        address.setCity(formRequest.getCity());
        address.setCountryCode(formRequest.getCountryCode());
        address.setCountry(formRequest.getCountry());
        address.setUser(user);

        Adress savedAddress = adressRepository.save(address);
        
        return savedAddress;
    }
	}


	@Override
	public Set <GetAdress> getUserAdress (Long userId) {
		
		Optional<User> userToFind = userRepository.findById(userId);
		User user = userToFind.get();
		
		Set <Adress> adresses = user.getAdresses();
		Set <GetAdress> getAdresses = new HashSet<>();
		
		for (Adress adress : adresses) {
			GetAdress getAdress = new GetAdress();
			getAdress.setId(adress.getId());
			getAdress.setFirstName(adress.getFirstName());
			getAdress.setLastName(adress.getLastName());
			getAdress.setStreetName(adress.getStreetName());
			getAdress.setStreetNameOptional(adress.getStreetNameOptional());
			getAdress.setPostCode(adress.getPostCode());
			getAdress.setCity(adress.getCity());
			getAdress.setCountry(adress.getCountry());
			
			getAdresses.add(getAdress);
		
	} 
			return getAdresses;
	
	}

}	

   

	

