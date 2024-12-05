package com.group.teona.services;

import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.enums.EnumSub;

public interface WalletService {
	
	// Permet de créer un wallet et si on le souhaite une card ou un pass qui y est attaché
	Wallet addWallet(Wallet wallet, User user);
	
	
	Pass addPass(User user);
	Pass addTopUp(Long passId, EnumSub topUp);
	
	Card addCard(User user);
	Card addTopUp(Long cardId, Integer topUp);






}
