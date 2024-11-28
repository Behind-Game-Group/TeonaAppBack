package com.group.teona.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.group.teona.dto.LoginRequest;
import com.group.teona.dto.WalletRequest;
import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.Wallet;
import com.group.teona.services.UserService;
import com.group.teona.services.WalletService;

@RestController
@RequestMapping("auth")
public class AuthController {
	

    @GetMapping("test")
    public ResponseEntity tested (Authentication authentication){//il faut avoir un token pour que sa fonctionne
        return ResponseEntity.ok("it a test : "+authentication.getName());
   
        
    }
    
    @Autowired
	private WalletService walletService;
	
    @PostMapping("add/{userId}")
	public ResponseEntity addWallet(@PathVariable Long userId, @RequestBody WalletRequest walletRequest ) {
		Wallet wallet = walletRequest.getWallet();
		Card card = walletRequest.getCard();
		Pass pass = walletRequest.getPass();
		walletService.addWallet(userId, wallet, card, pass);
		return ResponseEntity.ok("Wallet ajouté avec succès");
	}

    

}

