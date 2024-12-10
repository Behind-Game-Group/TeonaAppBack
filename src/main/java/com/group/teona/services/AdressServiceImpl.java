package com.group.teona.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.dto.FormAdress;
import com.group.teona.dto.FormTeonaPass;
import com.group.teona.entities.Adress;
import com.group.teona.entities.Card;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.CardRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.repositories.WalletRepository;
;

@Service
public class AdressServiceImpl implements AdressService {
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    AdressRepository adressRepository;
	
	@Autowired
	WalletRepository walletRepository;
	
	@Autowired
    CardRepository cardRepository;

	
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


	@Override
    public void saveFormWithUser(FormAdress formRequest, User user) {
        Adress adress = mapToAdress(formRequest);
        adress.setUser(user);
        
        adressRepository.save(adress);
    }
	
	@Override 
	public void saveFormWithCard (Long walletId, FormAdress formRequest) {
		
		Optional<Wallet> wallet = walletRepository.findById(walletId);
			
		if( wallet != null) {
			
	       // Adress adress = mapToAdress(formRequest);
	        
			Adress adress = new Adress();
			
			Card card = new Card();
			card.setTopUp(0);
			card.setWallet(wallet.get());
			card.setActive(false);
			card.setAdress(adress);
		
			 cardRepository.save(card);
			adress.setNumber(formRequest.getNumber());
			adress.setStreetName(formRequest.getStreetName());
			adress.setStreetNameOptional(formRequest.getStreetNameOptional());
			adress.setPostCode(formRequest.getPostCode());
			adress.setCity(formRequest.getCity());
			adress.setCountry(formRequest.getCountry());
			
	        
			adressRepository.save(adress);
			
			
		}
		
        throw new IllegalArgumentException("Vous devrez possédez un wallet pour effectuer cette opération");

	}
	/*
	@Override
    public void saveFormWithoutUser(FormAdress formRequest) {
        Adress adress = mapToAdress(formRequest);
        adressRepository.save(adress);
    }
    
	@Override
    public void saveFormForCard(FormAdress formRequest, Long cardId) {
    	
    	Optional<Card> card = cardRepository.findById(cardId);
    	if(card.isPresent()) {
    		Card cardFind = card.get();
	        Adress adress = mapToAdress(formRequest);
	        adress.getCards().add(cardFind);
	        adressRepository.save(adress);
    	}
        throw new IllegalArgumentException("Card doesn't exist");

    }
	*/
    private Adress mapToAdress(FormAdress formRequest) {
        Adress adress = new Adress();
        adress.setNumber(formRequest.getNumber());
        adress.setStreetName(formRequest.getStreetName());
        adress.setStreetNameOptional(formRequest.getStreetNameOptional());
        adress.setPostCode(formRequest.getPostCode());
        adress.setCity(formRequest.getCity());
        adress.setCountry(formRequest.getCountry());
        return adress;
    }

	

}
