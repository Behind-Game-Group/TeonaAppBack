package com.group.teona.services;

import com.group.teona.entities.User;
import com.group.teona.enums.EnumRole;
import com.group.teona.repositories.UserRepository;


import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
	   @Autowired
	    UserRepository userRepository;

	    @Autowired
	    private EmailService emailService;

	   
	    
	    @Autowired
	     PasswordEncoder passwordEncoder;
	    
	    @Autowired
	    private AuthenticationManager authenticationManager;
	    
	    @Override
	    public User signUp (User user) {
	    	 String email = user.getEmail().trim().toLowerCase();
	    	  if (userRepository.existsByEmail(email)) {
	              throw new IllegalArgumentException("Email is already exist.");
	          } else {  	  String verificationCode = CodeGenerator.generateVerificationCode();
	          user.setVerificationCode(verificationCode);
	          user.setCodeExpirationTime(LocalDateTime.now().plusMinutes(15)); 
	          user.setVerified(false);
	          user.setPassword(passwordEncoder.encode(user.getPassword()));

	       //   emailService.sendVerificationEmail(user.getEmail(), verificationCode);


	          List<EnumRole> roles = new ArrayList<>();
		        roles.add(EnumRole.USER); 
		        user.setRole(roles);
			   
          userRepository.save(user);


	       return  user;}  
	    	  
	         
	    }
	    
	    /*
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

	       //   emailService.sendVerificationEmail(user.getEmail(), verificationCode);


          user.setAdresses(new HashSet<>());
        List<EnumRole> role=new ArrayList<>();role.add(EnumRole.User);
        user.setRole(role);
          userRepository.save(user);



	        Optional<User> newUser = userRepository.findByEmail(user.getEmail());
	        
	        for (Adress adresse:adresses){
	            adresse.setUser(newUser.get());
	            adressRepository.save(adresse);
	        }
	     

	       return  user;} 
	     */
	    


	    public Optional<User> login(String email, String pass){
	        if (!userRepository.existsByEmail(email)) {
	            throw new IllegalArgumentException("Email not found");
	        }
	        Optional<User> user=  userRepository.findByEmail(email);
	        if (pass == null || !passwordEncoder.matches(pass, user.get().getPassword())) {
	            throw new IllegalArgumentException("Invalid password");
	        }

	        return user;
	        }
	    
	    public boolean emailExists(String email) {
	        return userRepository.existsByEmail(email);
	    }

		@Override
		public void updateUser(User user) {
			userRepository.save(user);
			
		}
		
		public User findByEmail(String email) {
		    return userRepository.findByEmail(email)
		            .orElseThrow(() -> new RuntimeException("User not found"));
		}

	
		public User findByResetToken(String resetToken) {
		    return userRepository.findByResetToken(resetToken)
		            .orElseThrow(() -> new RuntimeException("Invalid reset token."));
		}
		
}
