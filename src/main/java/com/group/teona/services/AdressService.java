package com.group.teona.services;

import java.util.Set;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.dto.FormTeonaPass;

public interface AdressService {
	
	 
	 void saveFormWithUser(FormTeonaPass formRequest, User user);
	 void saveFormWithoutUser(FormTeonaPass formRequest);
	 

}
