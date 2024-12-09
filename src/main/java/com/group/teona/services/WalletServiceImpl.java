package com.group.teona.services;


import java.time.LocalDate;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.entities.Adress;
import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.enums.EnumSub;
import com.group.teona.repositories.AdressRepository;
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
	
	@Autowired
    AdressRepository adressRepository;
	
	// Ajouter un wallet si on a un compte user
	@Override
	public Wallet addWalletForUser(User user) {
		
			Wallet wallet = new Wallet();
			wallet.setUser(user);
			wallet.setPhoneNumber(user.getPhoneNumber());
			wallet.setCount(0);
			return walletRepository.save(wallet); 
			}
	
	
	// Ajouter un wallet si on a pas de compte user
	@Override
	public Wallet addWallet(String phoneNumber) {	
		
		Wallet wallet = new Wallet();
		wallet.setPhoneNumber(phoneNumber);
		wallet.setCount(0);
		System.out.println(wallet.getId());
		return walletRepository.save(wallet); 
			}
	
	
	
	@Override 
	public Pass addPass (User user) {
		
		Wallet walletUser = user.getWallet();
		
		if( walletUser != null) {
			Pass pass = new Pass();
			
			pass.setWallet(walletUser);
			pass.setActive(false);
			
			return passRepository.save(pass);
		}
		
        throw new IllegalArgumentException("Vous devrez possédez un wallet pour effectuer cette opération");
		
	}
	
	@Override
	public Pass addTopUp (Long passId, EnumSub topUp) {
		Optional<Pass> pass = passRepository.findById(passId);
		Pass passFind = pass.get();
		Wallet wallet = passFind.getWallet();
		
		if(topUp == EnumSub.DayPass) {
				passFind.setDateSubscription(LocalDate.now());
				passFind.setValideDuration(1.0);
				passFind.setActive(true);
			
				return passFind;
			}
		
		if(topUp == EnumSub.WeeklyPass) {
			passFind.setDateSubscription(LocalDate.now());
			passFind.setValideDuration(7.0);
			passFind.setActive(true);
		
			return passFind;
		}
		
		if(topUp == EnumSub.MounthlyPass) {
			passFind.setDateSubscription(LocalDate.now());
			passFind.setValideDuration(30.0);
			passFind.setActive(true);
		
			return passFind;
		}
		
		if(topUp == EnumSub.WeeklyPass) {
			passFind.setDateSubscription(LocalDate.now());
			passFind.setValideDuration(365.0);
			passFind.setActive(true);
		
			return passFind;
		}
		
		
        throw new IllegalArgumentException("Veuillez entrer un chiffre valide");
	}
	
	
	
	@Override 
	public Card addCard (User user) {
		
		
			Wallet walletUser = user.getWallet();
			
		if( walletUser != null) {
			Card card = new Card();
			card.setTopUp(0);
			card.setWallet(walletUser);
			card.setActive(false);
			card.setAdress(user.getAdresses().iterator().next());
					
			return cardRepository.save(card);
		}
		
        throw new IllegalArgumentException("Vous devrez possédez un wallet pour effectuer cette opération");

	}
	
	@Override 
	public Card addCard (Long walletId, Adress adress) {
		
		Optional<Wallet> wallet = walletRepository.findById(walletId);
			
		if( wallet != null) {
			adressRepository.save(adress);
			Card card = new Card();
			card.setTopUp(0);
			card.setWallet(wallet.get());
			card.setActive(false);
			card.setAdress(adress);
		
			return cardRepository.save(card);
		}
		
        throw new IllegalArgumentException("Vous devrez possédez un wallet pour effectuer cette opération");

	}
	
	
	
	@Override
	public Card addTopUp (Long cardId, Integer topUp) {
		Optional<Card> card = cardRepository.findById(cardId);
		Card cardFind = card.get();
		Wallet wallet = cardFind.getWallet();
		
		if(topUp > 0) {
			if(topUp <= wallet.getCount() ) {
				cardFind.setTopUp(topUp);
				cardFind.setActive(true);
			
				return cardRepository.save(cardFind);
			}
			double missingAmount = topUp - wallet.getCount();
			throw new IllegalArgumentException("Vous devez ajouter au moins  " + missingAmount + " sur votre wallet afin de faire cette opération");
		}
		
        throw new IllegalArgumentException("Veuillez entrer un chiffre valide");
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
	

	
	
	
	@Override
	public String useCard(Long cardId) {
		Optional<Card> card = cardRepository.findById(cardId);
		if (card.isPresent()) {
			Card cardFind = card.get();
					if(cardFind.getTopUp() > 0) {
						cardFind.setTopUp(cardFind.getTopUp() - 1);
						return "Trajet validé, solde restant : " + cardFind.getTopUp();
					}
					throw new RuntimeException("Solde épuisé, veuillez acheter une nouvelle carte");
		}
		throw new RuntimeException("Carte inexistante");
		
	}
*/
	


//package com.group.teona.services;
//
//
//import java.time.LocalDate;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.group.teona.entities.Card;
//import com.group.teona.entities.Pass;
//import com.group.teona.entities.User;
//import com.group.teona.entities.Wallet;
//import com.group.teona.enums.EnumSub;
//import com.group.teona.repositories.CardRepository;
//import com.group.teona.repositories.PassRepository;
//import com.group.teona.repositories.UserRepository;
//import com.group.teona.repositories.WalletRepository;
//
//@Service
//public class WalletServiceImpl implements WalletService  {
//	
//	@Autowired
//    UserRepository userRepository;
//	
//	@Autowired
//    WalletRepository walletRepository;
//	
//	@Autowired
//    CardRepository cardRepository;
//	
//	@Autowired
//    PassRepository passRepository;
//
//	@Override
//	public Wallet addWallet(Wallet wallet, Card card, Pass pass, User user) {
//		
//
//		Optional<User> user = userRepository.findById(userId);
//		if(user.isPresent()) {
//			
//			User userFind = user.get();
//
//			wallet.setUser(userFind);
//		
//					if(card != null) {
//						card.setWallet(wallet);
//						
//						if(card.getAmount() > 0) {
//							card.setActive(true);
//						}
//
//						cardRepository.save(card);
//						
//					//	wallet.getCards().add(card);
//					}
//					
//					if(pass != null) {
//						pass.setWallet(wallet);
//						pass.setActive(true);
//						pass.setDateSubscription(LocalDate.now());
//									
//						// Si le pass est annuel
//						if(pass.getSubscriptionTime().equals(EnumSub.YearlyPass)) {
//										pass.setValideDuration(365.0);
//						}
//						
//
//						// Si le pass est mensuel
//						if(pass.getSubscriptionTime().equals(EnumSub.MounthlyPass)) {
//										pass.setValideDuration(30.0);
//						}
//						
//						// Si le pass est hebdomadaire
//						if(pass.getSubscriptionTime().equals(EnumSub.WeeklyPass)) {
//										pass.setValideDuration(7.0);
//						}
//						
//						// Si le pass est journalier
//						if(pass.getSubscriptionTime().equals(EnumSub.DayPass)) {
//										pass.setValideDuration(1.0);
//						}
//						
//						passRepository.save(pass);
//												
//
//					}
//					
//
//					return wallet;
//	
//	}
//	
//	@Override 
//	public Pass addNewPass (Pass pass,  User user) {
//		
//			Wallet walletUser = user.getWallet();
//			pass.setWallet(walletUser);
//			pass.setActive(true);
//			pass.setDateSubscription(LocalDate.now());
//						
//			// Si le pass est annuel
//			if(pass.getSubscriptionTime().equals(EnumSub.YearlyPass)) {
//							pass.setValideDuration(365.0);
//			}
//			
//			// Si le pass est mensuel
//			if(pass.getSubscriptionTime().equals(EnumSub.MounthlyPass)) {
//							pass.setValideDuration(30.0);
//			}
//			
//			// Si le pass est hebdomadaire
//			if(pass.getSubscriptionTime().equals(EnumSub.WeeklyPass)) {
//							pass.setValideDuration(7.0);
//			}
//			
//			// Si le pass est journalier
//			if(pass.getSubscriptionTime().equals(EnumSub.DayPass)) {
//							pass.setValideDuration(1.0);
//			}
//			
//			return passRepository.save(pass);
//		
//		
//	}
//	
//	@Override 
//	public Card addNewCard (User user) {
//		
//			Wallet walletUser = user.getWallet();
//			
//		
//			Card card = new Card();
//			card.setTopUp(0);
//			card.setWallet(walletUser);
//			card.setActive(false);
//			
//		
//			return cardRepository.save(card);
//		
//	}
//	
//	@Override
//	public Card add5TopUp (Long cardId) {
//		Optional<Card> card = cardRepository.findById(cardId);
//		Card cardFind = card.get();
//		
//		cardFind.setTopUp(5);
//		cardFind.setActive(true);
//		
//		return cardFind;
//	}
//	
//	@Override
//	public Card add10TopUp (Long cardId) {
//		Optional<Card> card = cardRepository.findById(cardId);
//		Card cardFind = card.get();
//		
//		cardFind.setTopUp(10);
//		cardFind.setActive(true);
//		
//		return cardFind;
//	}
//	
//	@Override
//	public Card add15TopUp (Long cardId) {
//		Optional<Card> card = cardRepository.findById(cardId);
//		Card cardFind = card.get();
//		
//		cardFind.setTopUp(15);
//		cardFind.setActive(true);
//		
//		return cardFind;
//	}
//	
//	@Override
//	public Card add20TopUp (Long cardId) {
//		Optional<Card> card = cardRepository.findById(cardId);
//		Card cardFind = card.get();
//		
//		cardFind.setTopUp(20);
//		cardFind.setActive(true);
//		
//		return cardFind;
//	}
//	
//	@Override
//	public Card addPersoTopUp (Long cardId, Long topUp) {
//		Optional<Card> card = cardRepository.findById(cardId);
//		Card cardFind = card.get();
//		
//		cardFind.setTopUp(topUp);
//		cardFind.setActive(true);
//		
//		return cardFind;
//	}
//	
//	
//	/*
//	@Override
//	public String useCard(Long cardId) {
//		Optional<Card> card = cardRepository.findById(cardId);
//		if (card.isPresent()) {
//			Card cardFind = card.get();
//					if(cardFind.isActive()) {
//						cardFind.setAmount(cardFind.getAmount() - 1);
//							if(cardFind.getAmount() == 0) {
//								cardFind.setActive(false);
//							}
//						return "Trajet validé, solde restant : " + cardFind.getAmount();
//					}
//					throw new RuntimeException("Solde épuisé, veuillez acheter une nouvelle carte");
//		}
//		throw new RuntimeException("Carte inexistante");
//		
//	}
//	*/
//
//	
//	@Override 
//	public Card addNewCard (Long userId, Card card) {
//		
//		Optional<User> user = userRepository.findById(userId);
//		if(user.isPresent()) {
//			
//			User userFind = user.get();
//			Wallet walletUser = userFind.getWallet();
//			// walletUser.getCards().add(card);
//			card.setWallet(walletUser);
//			
//			return cardRepository.save(card);
//		}
//		throw new RuntimeException("Utilisateur non trouvé");
//		
//	}
//	
//	
//	@Override
//	public String useCard(Long cardId) {
//		Optional<Card> card = cardRepository.findById(cardId);
//		if (card.isPresent()) {
//			Card cardFind = card.get();
//					if(cardFind.getAmount() > 0) {
//						cardFind.setAmount(cardFind.getAmount() - 1);
//						return "Trajet validé, solde restant : " + cardFind.getAmount();
//					}
//					throw new RuntimeException("Solde épuisé, veuillez acheter une nouvelle carte");
//		}
//		throw new RuntimeException("Carte inexistante");
//		
//	}
//
//	
//
//
}
