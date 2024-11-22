package com.group.teona.services;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.enums.EnumRole;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.UserRepository;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AdressRepository adressRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public String signUp (User user, Set<Adress> adresses) {
    	user.setRole(EnumRole.User);
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	
    	user.setAdresses(adresses);
    	for (Adress adress : adresses) 
    	{ adress.setUser(user); }
    	
    	userRepository.save(user);
    	adressRepository.saveAll(adresses);
    	
    	return "User enregistré";
	}

}
