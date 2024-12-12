package com.group.teona.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.dto.FormAdress;
import com.group.teona.dto.FormTeonaPass;

@Service
public interface AdressService {
	

	
	public Adress saveAddress(FormTeonaPass formRequest, User user);
	

}
