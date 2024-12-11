package com.group.teona.services;

import java.util.Set;

import com.group.teona.entities.Adess;
import com.group.teona.entities.User;
import com.group.teona.dto.FormAdress;
import com.group.teona.dto.FormTeonaPass;

public interface AdressService {
	

	
	 void saveAddress(FormTeonaPass formRequest, User user);
	 void savePass(FormTeonaPass.PassData passData, User user);

}
