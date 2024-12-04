package com.group.teona.controller;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.repositories.UserRepository;
import com.group.teona.services.AdressService;

@RestController
@RequestMapping("auth")
public class AdressController {
    
    @Autowired
	private AdressService adressService;
	 
    @Autowired
    UserRepository userRepository;
    
    
    @PostMapping("addAdress")
    public ResponseEntity addAdress(@RequestBody Set<Adress> adresses, Authentication authentication) {
    	
    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());

    	adressService.addAdresses(adresses, userFind.get());

    	return ResponseEntity.ok("Adresse(s) enregistrée(s)");
    }
    

    
    }
    
 



