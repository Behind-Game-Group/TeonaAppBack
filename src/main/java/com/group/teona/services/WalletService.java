package com.group.teona.services;

import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.Wallet;

public interface WalletService {
	
	Wallet addWallet(Long userId, Wallet wallet, Card card, Pass pass);


}
