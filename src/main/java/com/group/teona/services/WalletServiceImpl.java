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
		
					if(card != null) {
						card.setWallet(wallet);
						
						if(card.getAmount() > 0) {
							card.setActive(true);
						}
						cardRepository.save(card);
						
						wallet.getCards().add(card);
					}
					
					if(pass != null) {
						pass.setWallet(wallet);
						passRepository.save(pass);
						
						// passer en isActive = true 
						
						wallet.setPass(pass);
					}
					
					walletRepository.save(wallet);					
					return wallet;
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}
	
	@Override 
	public Card addNewCard (Long userId, Card card) {
		
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			
			User userFind = user.get();
			Wallet walletUser = userFind.getWallet();
			// walletUser.getCards().add(card);
			card.setWallet(walletUser);
			
			return cardRepository.save(card);
		}
		throw new RuntimeException("Utilisateur non trouvé");
		
	}
	
	
	@Override
	public String useCard(Long cardId) {
		Optional<Card> card = cardRepository.findById(cardId);
		if (card.isPresent()) {
			Card cardFind = card.get();
					if(cardFind.getAmount() > 0) {
						cardFind.setAmount(cardFind.getAmount() - 1);
						return "Trajet validé, solde restant : " + cardFind.getAmount();
					}
					throw new RuntimeException("Solde épuisé, veuillez acheter une nouvelle carte");
		}
		throw new RuntimeException("Carte inexistante");
		
	}

	

}
