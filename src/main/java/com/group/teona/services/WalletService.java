package com.group.teona.services;

import com.group.teona.entities.Adress;
import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.enums.EnumSub;

public interface WalletService {
	
	Wallet addWalletForUser(User user);
	Wallet addWallet(String phoneNumber);
	
	
	Pass addPass(User user);
	Pass addTopUp(Long passId, EnumSub topUp);
	
	Card addCard(User user);
	Card addCard(Long walletId, Adress adress);
	Card addTopUp(Long cardId, Integer topUp);








}
