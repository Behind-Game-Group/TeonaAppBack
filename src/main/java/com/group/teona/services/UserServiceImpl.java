package com.group.teona.services;

import com.group.teona.entities.User;
import com.group.teona.enums.EnumRole;
import com.group.teona.repositories.UserRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
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
    public User signUp (User user) {
    	  if (userRepository.existsByEmail(user.getEmail())) {
              throw new IllegalArgumentException("Email is already exist.");
          }
    	  
    	  String verificationCode = CodeGenerator.generateVerificationCode();
          user.setVerificationCode(verificationCode);
          user.setCodeExpirationTime(LocalDateTime.now().plusMinutes(15)); 
          user.setVerified(false);
          user.setPassword(passwordEncoder.encode(user.getPassword()));
          emailService.sendVerificationEmail(user.getEmail(), verificationCode);
    	user.setRole(EnumRole.User);
    	return userRepository.save(user);
	}

}
