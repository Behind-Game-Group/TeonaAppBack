package com.group.teona.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.dto.PassRequestDto;
import com.group.teona.entities.Adress;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.PassRepository;
import com.group.teona.repositories.UserRepository;

@Service
public class PassServiceImpl implements PassService {
	  @Autowired
	    private PassRepository passRepository;
	  
	  @Autowired
	    private AdressRepository adressRepository;

	  
	    public void savePass(PassRequestDto passRequest, User user,Long adressId) {
	    	
	    	  Optional<Adress> optionalAdress = adressRepository.findById(adressId); 

	          if (optionalAdress.isEmpty()) {
	              throw new IllegalArgumentException("Address with ID " + adressId + " not found");
	          }

	          Adress adress = optionalAdress.get();
	          
	        Pass pass = new Pass();
	        pass.setCardTitle(passRequest.getCardTitle());
	        pass.setCardPrice(passRequest.getCardPrice());
	        pass.setIsActive(passRequest.isActive());
	        pass.setUser(user);
	        pass.setAdress(adress);

	        passRepository.save(pass);
	    }

	
}
