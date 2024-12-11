package com.group.teona.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.dto.FormAdress;
import com.group.teona.dto.FormTeonaPass;
import com.group.teona.entities.Adess;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.CardRepository;
import com.group.teona.repositories.PassRepository;
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
	private PassRepository passRepository;

	@Override
    public void saveAddress(FormTeonaPass formRequest, User user) {
        Adess address = new Adess();
        address.setFirstName(formRequest.getFirstName());
        address.setLastName(formRequest.getLastName());
        address.setStreetName(formRequest.getStreetName());
        address.setStreetNameOptional(formRequest.getStreetNameOptional());
        address.setPostCode(formRequest.getPostCode());
        address.setCity(formRequest.getCity());
        address.setPhoneNumber(formRequest.getPhoneNumber());
        address.setCountry(formRequest.getCountry());
        address.setImage(formRequest.getImage());
        address.setUser(user);

        adressRepository.save(address);
    }
	
	   public void savePass(FormTeonaPass.PassData passData, User user) {
	        Pass pass = new Pass();
	        pass.setCardTitle(passData.getCardTitle());
	        pass.setCardPrice(passData.getCardPrice());
	        pass.setIsActive(passData.isActive());
	        pass.setUser(user);

	        passRepository.save(pass);
	    }

   

	

}
