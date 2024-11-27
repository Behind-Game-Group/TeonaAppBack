package com.group.teona.services;

import com.group.teona.dto.LoginRequest;
import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.enums.EnumRole;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdressRepository adressRepository;
    
    @Autowired
     PasswordEncoder passwordEncoder;
    
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

        return Optional.empty();}
}
