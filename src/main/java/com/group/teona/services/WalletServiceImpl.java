package com.group.teona.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.CardRepository;
import com.group.teona.repositories.PassRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.repositories.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService  {
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    WalletRepository walletRepository;
	
	@Autowired
    CardRepository cardRepository;
	
	@Autowired
    PassRepository passRepository;

	@Override
	public Wallet addWallet(Long userId, Wallet wallet, Card card, Pass pass) {
		
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			
			User userFind = user.get();
			
			wallet.setUser(userFind);
			walletRepository.save(wallet);
		
					if(card != null) {
						card.setWallet(wallet);
						cardRepository.save(card);
					}
					
					if(pass != null) {
						pass.setWallet(wallet);
						passRepository.save(pass);
					}
					
					walletRepository.save(wallet);					
					return wallet;
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}
	

}
