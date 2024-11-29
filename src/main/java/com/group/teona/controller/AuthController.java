package com.group.teona.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.group.teona.dto.LoginRequest;
import com.group.teona.dto.WalletRequest;
import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.CardRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.repositories.WalletRepository;
import com.group.teona.services.UserService;
import com.group.teona.services.WalletService;

@RestController
@RequestMapping("auth")
public class AuthController {
	

    @GetMapping("test")
    public ResponseEntity tested (Authentication authentication){//il faut avoir un token pour que sa fonctionne
        return ResponseEntity.ok("it a test : ");
   
        
    }
    
    @Autowired
	private WalletService walletService;
	 
    @Autowired
	private UserRepository userRepository;

    @PostMapping("add")
	public ResponseEntity addWallet(@RequestParam Long userId, @RequestBody WalletRequest walletRequest ) {
		Wallet wallet = walletRequest.getWallet();
		Card card = walletRequest.getCard();
		Pass pass = walletRequest.getPass();
		walletService.addWallet(userId, wallet, card, pass);

		return ResponseEntity.ok("Wallet ajouté avec succès");
	}
    
    @PostMapping("addCard")
    public ResponseEntity addCard(@RequestParam Long userId, @RequestBody Card card, Authentication authentication) {
    	
    	System.out.println(userRepository.findByEmail(authentication.getName()));
  
    	walletService.addNewCard(userId, card);

    	return ResponseEntity.ok("Carte ajoutée avec succès");
    }
    
    @PostMapping("addPass")
    public ResponseEntity addPass(@RequestParam Long userId, @RequestBody Pass pass) {
    	walletService.addNewPass(userId, pass);

    	return ResponseEntity.ok("Pass créé avec succès");
    }
    
    /*
    @PutMapping("useCard")
    public String useCard(Long cardId) {	
    	return walletService.useCard(cardId);
    }
    */
    
    }
    
 



