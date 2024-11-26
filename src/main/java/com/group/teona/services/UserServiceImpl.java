package com.group.teona.services;

import com.group.teona.dto.LoginRequest;
import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.enums.EnumRole;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
    private AuthenticationManager authenticationManager;
    
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
          emailService.sendVerificationEmail(user.getEmail(), verificationCode);

          user.setAdresses(new HashSet<>());
        List<EnumRole> role=new ArrayList<>();role.add(EnumRole.User);
        user.setRole(role);
    	   user.setAdresses(new HashSet<>());
          userRepository.save(user);

        Optional<User> newUser = userRepository.findByEmail(user.getEmail());
        
        for (Adress adresse:adresses){
            adresse.setUser(newUser.get());
            adressRepository.save(adresse);
        }

       return  user;}
    
    @Override
	public String logIn (LoginRequest loginRequest) {
    	String email = loginRequest.getEmail();
    	String password = loginRequest.getPassword();
    	
    	try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            System.out.println(authentication);
    		return "User connecté";
    		// UsernamePasswordAuthenticationToken implémente une interface authentication 
    		// On lui donne des données à encapsuler (email et password) qu'elle va transmettre à authenticationManager
    				}
    		
    	catch (Exception e) {
    		System.out.println(e);
    		return "Nom d'utilisateur ou mot de passe incorrect";
    				} 
    }


    public Optional<User> login(String email, String pass){
        if (!userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email not found");
        }
        Optional<User> user=  userRepository.findByEmail(email);
       if(passwordEncoder.matches(pass,user.get().getPassword())) return user;

        return null;}
}
