package com.group.teona.services;

import java.util.Set;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;

public interface UserService {
	
	 public String signUp (User user, Set<Adress> adresses) ;

}
