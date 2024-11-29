package com.group.teona.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.group.teona.dto.WalletRequest;
import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.UserRepository;
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
	public ResponseEntity addWallet(@RequestParam Long userId, @RequestBody WalletRequest walletRequest, Authentication authentication ) {
		Wallet wallet = walletRequest.getWallet();
		Card card = walletRequest.getCard();
		Pass pass = walletRequest.getPass();
    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());

		walletService.addWallet(wallet, card, pass, userFind.get() );

		return ResponseEntity.ok("Wallet ajouté avec succès");
	}
    
    @PostMapping("addCard")
    public ResponseEntity addCard(@RequestBody Card card, Authentication authentication) {
    	
    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
    	
    	walletService.addNewCard(card, userFind.get() );

    	return ResponseEntity.ok("Carte ajoutée avec succès");
    }
    
    @PostMapping("addPass")
    public ResponseEntity addPass(@RequestBody Pass pass, Authentication authentication) {
    	
    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());

    	walletService.addNewPass(pass, userFind.get());

    	return ResponseEntity.ok("Pass créé avec succès");
    }
    
    /*
    @PutMapping("useCard")
    public String useCard(Long cardId) {	
    	return walletService.useCard(cardId);
    }
    */
    
    }
    
 



