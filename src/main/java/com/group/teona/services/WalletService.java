package com.group.teona.services;

import java.util.Optional;

import com.group.teona.entities.User;

public interface WalletService  {
	  boolean hasWallet(User user);
	    Optional<Long> getWalletIdByUser(User user);
}
