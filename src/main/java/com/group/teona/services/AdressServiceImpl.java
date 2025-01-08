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
    public Adress saveAddress(FormTeonaPass formRequest, User user) {
        Adress address = new Adress();
        address.setFirstName(formRequest.getFirstName());
        address.setLastName(formRequest.getLastName());
        address.setStreetName(formRequest.getStreetName());
        address.setStreetNameOptional(formRequest.getStreetNameOptional());
        address.setPostCode(formRequest.getPostCode());
        address.setCity(formRequest.getCity());
        address.setPhoneNumber(formRequest.getPhoneNumber());
        address.setCountry(formRequest.getCountry());
        address.setUser(user);

        Adress savedAddress = adressRepository.save(address);
        
        return savedAddress;
    }


	@Override
	public Set <GetAdress> getUserAdress (User user) {
		
		Set <Adress> adresses = user.getAdresses();
		Set <GetAdress> getAdresses = new HashSet<>();
		
		for (Adress adress : adresses) {
			GetAdress getAdress = new GetAdress();
			getAdress.setId(adress.getId());
			getAdress.setFirstName(adress.getFirstName());
			getAdress.setLastName(adress.getLastName());
			getAdress.setPhoneNumber(adress.getPhoneNumber());
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

   

	

