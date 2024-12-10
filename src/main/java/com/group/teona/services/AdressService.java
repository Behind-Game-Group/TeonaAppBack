package com.group.teona.services;

import java.util.Set;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.dto.FormAdress;
import com.group.teona.dto.FormTeonaPass;

public interface AdressService {
	
	void saveFormWithUser(FormAdress formRequest, User user);

	void saveFormWithCard(Long walletId, FormAdress formRequest);
	
	// void saveFormWithoutUser(FormTeonaPass formRequest);
	 
	// void saveFormForCard(FormTeonaPass formRequest, Long cardId);

}
