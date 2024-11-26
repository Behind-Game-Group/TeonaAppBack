package com.group.teona.services;

import java.util.Optional;
import java.util.Set;

import com.group.teona.dto.LoginRequest;
import com.group.teona.entities.Adress;
import com.group.teona.entities.User;

public interface UserService {


	public Optional<User> login(String email, String pass);
	
	User signUp (User user, Set<Adress> adresses) ;

	String logIn (LoginRequest loginRequest);
	public boolean emailExists(String email);
}
