package com.group.teona.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.entities.User;
import com.group.teona.repositories.UserRepository;
import com.group.teona.services.WalletService;

@RestController
@RequestMapping("/api")
public class WalletController {

	@Autowired
    private WalletService walletService;
	
	 @Autowired
	 private UserRepository userRepository; 

	    
	    @GetMapping("/has-wallet/{userId}")
	    public ResponseEntity<?> checkIfUserHasWallet(@PathVariable Long userId) {
	        Optional<User> user = userRepository.findById(userId);
	        
	        if (user.isEmpty()) {
	            return ResponseEntity.status(404).body("User not found");
	        }

	        Optional<Long> walletId = walletService.getWalletIdByUser(user.get());

	        if (walletId.isPresent()) {
	            Map<String, Object> response = new HashMap<>();
	            response.put("message", "User has a wallet");
	            response.put("walletId", walletId.get());
	            return ResponseEntity.ok(response);

	     
	        } else {
	            return ResponseEntity.ok("User does not have a wallet");
	        }
}
}
