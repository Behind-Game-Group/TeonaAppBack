package com.group.teona.services;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.enums.EnumRole;
import com.group.teona.repositories.AdressRepo;
import com.group.teona.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdressRepo adressRepo;
    
    @Autowired
     PasswordEncoder passwordEncoder;
    
    
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

        List<EnumRole> role=new ArrayList<>();role.add(EnumRole.User);
        user.setRole(role);
    	   user.setAdresses(new HashSet<>());
          userRepository.save(user);

        Optional<User> newUser = userRepository.findByEmail(user.getEmail());
        for (Adress adresse:adresses){
            adresse.setUser(newUser.get());
            adressRepo.save(adresse);
        }

       return  user;}

    public Optional<User> login(String email, String pass){
        if (!userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email not found");
        }
        Optional<User> user=  userRepository.findByEmail(email);
       if(passwordEncoder.matches(pass,user.get().getPassword())) return user;


        return null;}
}
