package com.group.teona.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.UserRepository;
;

@Service
public class AdressServiceImpl implements AdressService {
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    AdressRepository adressRepository;
	
	@Override 
	public Set<Adress> addAdresses (Set<Adress> adresses, User user) {

			for (Adress adress : adresses) {
				adress.setUser(user);
			}
			adressRepository.saveAll(adresses);
			
			return adresses;
		
	}

}
