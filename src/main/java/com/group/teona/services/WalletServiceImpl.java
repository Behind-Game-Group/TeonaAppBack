package com.group.teona.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {

	  @Autowired
	    private WalletRepository walletRepository;

	    public boolean hasWallet(User user) {
	        Optional<Wallet> wallet = walletRepository.findByUser(user);
	        return wallet.isPresent();
	    }
	    public Optional<Long> getWalletIdByUser(User user) {
	        return walletRepository.findByUser(user)
	                .map(Wallet::getId); 
	    }
	    
}
