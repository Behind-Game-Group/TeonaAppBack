package com.group.teona.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.enums.EnumSub;
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
	public Wallet addWallet(Wallet wallet, Card card, Pass pass, User user) {
		

			wallet.setUser(user);
			walletRepository.save(wallet);
			
					if(card != null) {
						card.setWallet(wallet);
						card.setActive(true);
						cardRepository.save(card);
						
						wallet.getCards().add(card);
					}
					
					if(pass != null) {
						// Si le pass est annuel
						if(pass.getSubscriptionTime().equals(EnumSub.YearlyPass)) {
										pass.setValideDuration(365.0);
						}
						
						// Si le pass est mensuel
						if(pass.getSubscriptionTime().equals(EnumSub.MounthlyPass)) {
										pass.setValideDuration(30.0);
						}
						
						// Si le pass est hebdomadaire
						if(pass.getSubscriptionTime().equals(EnumSub.WeeklyPass)) {
										pass.setValideDuration(7.0);
						}
						
						// Si le pass est journalier
						if(pass.getSubscriptionTime().equals(EnumSub.DayPass)) {
										pass.setValideDuration(1.0);
						}
						
						pass.setDateSubscription(LocalDate.now());
						pass.setWallet(wallet);
						pass.setActive(true);
						passRepository.save(pass);
												
						wallet.setPass(pass);
					}
					

					return wallet;
	
	}
	
	@Override 
	public Card addNewCard (Card card, User user) {
			Wallet walletUser = user.getWallet();
			card.setWallet(walletUser);
			card.setActive(true);
			
		
		return cardRepository.save(card);
		
	}
	
	@Override 
	public Pass addNewPass (Pass pass,  User user) {
		
			Wallet walletUser = user.getWallet();
			pass.setWallet(walletUser);
			pass.setActive(true);
			pass.setDateSubscription(LocalDate.now());
						
			// Si le pass est annuel
			if(pass.getSubscriptionTime().equals(EnumSub.YearlyPass)) {
							pass.setValideDuration(365.0);
			}
			
			// Si le pass est mensuel
			if(pass.getSubscriptionTime().equals(EnumSub.MounthlyPass)) {
							pass.setValideDuration(30.0);
			}
			
			// Si le pass est hebdomadaire
			if(pass.getSubscriptionTime().equals(EnumSub.WeeklyPass)) {
							pass.setValideDuration(7.0);
			}
			
			// Si le pass est journalier
			if(pass.getSubscriptionTime().equals(EnumSub.DayPass)) {
							pass.setValideDuration(1.0);
			}
			
			return passRepository.save(pass);
		
		
	}
	
	/*
	@Override
	public String useCard(Long cardId) {
		Optional<Card> card = cardRepository.findById(cardId);
		if (card.isPresent()) {
			Card cardFind = card.get();
					if(cardFind.isActive()) {
						cardFind.setAmount(cardFind.getAmount() - 1);
							if(cardFind.getAmount() == 0) {
								cardFind.setActive(false);
							}
						return "Trajet validé, solde restant : " + cardFind.getAmount();
					}
					throw new RuntimeException("Solde épuisé, veuillez acheter une nouvelle carte");
		}
		throw new RuntimeException("Carte inexistante");
		
	}
	*/

	

}
