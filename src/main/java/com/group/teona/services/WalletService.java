package com.group.teona.services;

import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;

public interface WalletService {
	
	// Permet de créer un wallet et si on le souhaite une card ou un pass qui y est attaché
	Wallet addWallet(Wallet wallet, Card card, Pass pass, User user);
	
	// Permet de créer un pass
	Pass addNewPass(Pass pass,  User user);
	
	// Permet de créer une card
	Card addNewCard( Card card, User user);


}
