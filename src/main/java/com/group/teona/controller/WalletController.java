package com.group.teona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.dto.WalletRequest;
import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.Wallet;
import com.group.teona.services.WalletService;

@RestController
@RequestMapping("wallet")
public class WalletController {
	
	@Autowired
	private WalletService walletService;
	
	
	@PostMapping("add")
	public ResponseEntity addWallet(@RequestParam Long userId, @RequestBody WalletRequest walletRequest ) {
		Wallet wallet = walletRequest.getWallet();
		Card card = walletRequest.getCard();
		Pass pass = walletRequest.getPass();
		walletService.addWallet(userId, wallet, card, pass);
		return ResponseEntity.ok("Wallet ajouté avec succès");
	}


}
