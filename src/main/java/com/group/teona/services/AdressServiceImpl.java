package com.group.teona.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.entities.Adress;
import com.group.teona.entities.Card;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.CardRepository;
import com.group.teona.repositories.UserRepository;
;

@Service
public class AdressServiceImpl implements AdressService {
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    AdressRepository adressRepository;
	
	@Autowired
    CardRepository cardRepository;

	@Override 
	public Set<Adress> addAdresses (Set<Adress> adresses, User user) {

			for (Adress adress : adresses) {
				adress.setUser(user);
				adress.setFirstName(user.getFirstName());
				adress.setLastName(user.getLastName());
			}
			adressRepository.saveAll(adresses);
			
			return adresses;
		
	}
	
	/*
	@Override 
	public Adress addAdressForCard (Adress adresse, Long cardId) {
		
			Optional<Card> card = cardRepository.findById(cardId);
			
			if(card != null) {
				Card cardFind = card.get();
				Wallet wallet = cardFind.getWallet();
				User user = wallet.getUser();
				if(user != null) {
						adresse.getCards().add(card.get());
						adressRepository.save(adresse);
						
						return adresse;
				}
		        throw new IllegalArgumentException("Adresse deja associée au compte utilisateur");
			}
	        throw new IllegalArgumentException("Vous devrez possédez une carte pour effectuer cette opération");

		
	}
	*/


}
