package com.group.teona.services;

import java.util.Optional;
import java.util.Set;

import com.group.teona.entities.Adess;
import com.group.teona.entities.User;

public interface UserService {


	public Optional<User> login(String email, String pass);
	
	public User signUp (User user) ;

	public boolean emailExists(String email);
	public User findByEmail(String email);
	public void updateUser(User user);
	public User findByResetToken(String resetToken);
	
}
