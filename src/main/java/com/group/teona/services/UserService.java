package com.group.teona.services;

import java.util.Set;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;

public interface UserService {
	
	 public User signUp (User user, Set<Adress> adresses) ;

}
