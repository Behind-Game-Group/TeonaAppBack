package com.group.teona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("add/{userId}")
	@PreAuthorize("hasAuthority('User')")
	public ResponseEntity addWallet(@PathVariable Long userId, @RequestBody WalletRequest walletRequest, Authentication authentication ) {
		System.out.println(walletRequest.toString());
		Wallet wallet = walletRequest.getWallet();
		Card card = walletRequest.getCard();
		Pass pass = walletRequest.getPass();
		walletService.addWallet(userId, wallet, card, pass);
		return ResponseEntity.ok("Wallet ajouté avec succès");
	}


}
