package com.group.teona.services;

import java.util.Set;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;

public interface AdressService {
	
	public Set<Adress> addAdresses (Set<Adress> adresses, User user);

}
