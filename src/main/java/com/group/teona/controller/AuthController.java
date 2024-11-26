package com.group.teona.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group.teona.dto.LoginRequest;
import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.Wallet;
import com.group.teona.services.UserService;
import com.group.teona.services.WalletService;

@RestController
@RequestMapping("auth")
public class AuthController {
	

    @GetMapping("test")
    public ResponseEntity tested (){
        return ResponseEntity.ok("it a test");
   
        
    }
    
    @Autowired
	private WalletService walletService;
	
	@PostMapping("add")
	public ResponseEntity addWallet(@PathVariable Long userId, @RequestBody Wallet wallet, @RequestBody(required=false) Card card,
									@RequestBody(required=false)  Pass pass) {
		walletService.addWallet(userId, wallet, card, pass);
		return ResponseEntity.ok("Wallet ajouté avec succès");
	}
    

}

