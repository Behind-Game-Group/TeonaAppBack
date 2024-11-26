package com.group.teona.services;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.enums.EnumRole;
import com.group.teona.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @Override
    public User signUp (User user, Set<Adress> adresses) {
    	  if (userRepository.existsByEmail(user.getEmail())) {
              throw new IllegalArgumentException("Email is already exist.");
          }
    	  
    	  String verificationCode = CodeGenerator.generateVerificationCode();
          user.setVerificationCode(verificationCode);
          user.setCodeExpirationTime(LocalDateTime.now().plusMinutes(15)); 
          user.setVerified(false);
          user.setPassword(passwordEncoder.encode(user.getPassword()));
       	user.setRole(EnumRole.User);
       	for (Adress adress : adresses) {
            adress.setUser(user);
        }
        user.setAdresses(adresses);
        
        User savedUser = userRepository.save(user);
          emailService.sendVerificationEmail(user.getEmail(), verificationCode);
   
   
    	return savedUser;
	}

}
