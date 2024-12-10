package com.group.teona.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.group.teona.dto.FormTeonaPass;
import com.group.teona.entities.Adress;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.PassRepository;

public class PassServiceImpl implements PassService {
	
	@Autowired
    PassRepository passRepository;

	@Override
	public void saveFormPass(FormTeonaPass formRequest, User user) {
		// TODO Auto-generated method stub
		
	
		Wallet walletUser = user.getWallet();
		
		if( walletUser != null) {
		
		Pass teonaPass = new Pass();
		teonaPass.setFirstName(formRequest.getFirstName());
		teonaPass.setLastName(formRequest.getLastName());
		teonaPass.setPhoneNumber(formRequest.getPhoneNumber());
		teonaPass.setStreetName(formRequest.getStreetName());
		teonaPass.setStreetNameOptional(formRequest.getStreetNameOptional());
		teonaPass.setPostCode(formRequest.getPostCode());
		teonaPass.setCity(formRequest.getCity());
		teonaPass.setCountry(formRequest.getCountry());
		teonaPass.setImage(formRequest.getImage());
		teonaPass.setSubscriptionTime(formRequest.getSubscriptionTime());
		teonaPass.setDateSubscription((LocalDate.now()));
		teonaPass.setActive(true);
		teonaPass.setWallet(walletUser);
		passRepository.save(teonaPass);
        }
	}
	


}
