package com.group.teona.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.dto.FormTeonaPass;
import com.group.teona.dto.GetAdress;

@Service
public interface AdressService {
	

	
	public Adress saveOrUpdateAddress(FormTeonaPass formRequest, User user);

	Set<GetAdress> getUserAdress(Long userId);
	

}

