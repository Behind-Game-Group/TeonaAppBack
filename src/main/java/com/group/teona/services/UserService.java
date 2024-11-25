package com.group.teona.services;

import java.util.Set;

import com.group.teona.dto.LoginRequest;
import com.group.teona.entities.Adress;
import com.group.teona.entities.User;

public interface UserService {
	
	User signUp (User user, Set<Adress> adresses) ;

	String logIn (LoginRequest loginRequest);

}
