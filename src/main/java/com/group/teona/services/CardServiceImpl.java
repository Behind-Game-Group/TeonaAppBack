package com.group.teona.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.dto.FormTeonaCard;
import com.group.teona.dto.FormTeonaPass;
import com.group.teona.entities.Adress;
import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.CardRepository;
import com.group.teona.repositories.PassRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.repositories.WalletRepository;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    CardRepository cardRepository;
	
	@Autowired
    WalletRepository walletRepository;
	
	@Autowired
    AdressRepository adressRepository;
	
	// Créé un wallet, une card et une adresse associée pour l'user authentifié
	@Override
	public void saveFormCardWithUser (FormTeonaCard formRequest, User user) {
		
		Adress adress = new Adress();
		adress.setFirstName(formRequest.getFirstName());
		adress.setLastName(formRequest.getLastName());
		adress.setPhoneNumber(formRequest.getPhoneNumber());
		adress.setStreetName(formRequest.getStreetName());
		adress.setStreetNameOptional(formRequest.getStreetNameOptional());
		adress.setPostCode(formRequest.getPostCode());
		adress.setCity(formRequest.getCity());
		adress.setCountry(formRequest.getCountry());
		adress.setUser(user);
		
		adressRepository.save(adress);
				
		Card teonaCard = new Card();
		teonaCard.setActive(true);
		teonaCard.setAdress(adress);
		
		if (user.getWallet() == null) {
			Wallet wallet = new Wallet();
			wallet.setUser(user);
			wallet.setPhoneNumber(user.getPhoneNumber());
			teonaCard.setWallet(wallet);
			walletRepository.save(wallet);

		}
		else {
			Wallet wallet = user.getWallet();
			teonaCard.setWallet(wallet);
			
			walletRepository.save(wallet);
		}
		
		cardRepository.save(teonaCard);
		

	}
	
	@Override
	public void saveFormCardWithoutUser (FormTeonaCard formRequest) {
		
		Adress adress = new Adress();
		adress.setFirstName(formRequest.getFirstName());
		adress.setLastName(formRequest.getLastName());
		adress.setPhoneNumber(formRequest.getPhoneNumber());
		adress.setStreetName(formRequest.getStreetName());
		adress.setStreetNameOptional(formRequest.getStreetNameOptional());
		adress.setPostCode(formRequest.getPostCode());
		adress.setCity(formRequest.getCity());
		adress.setCountry(formRequest.getCountry());

		adressRepository.save(adress);
		
		Card teonaCard = new Card();
		teonaCard.setActive(true);
		teonaCard.setAdress(adress);
		
		
		Wallet wallet = new Wallet();
		wallet.setPhoneNumber(formRequest.getPhoneNumber());
		
		walletRepository.save(wallet);
		

		teonaCard.setWallet(wallet);
		
		cardRepository.save(teonaCard);
		
	}
	


}
