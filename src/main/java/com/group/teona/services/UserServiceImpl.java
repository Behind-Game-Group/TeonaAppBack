package com.group.teona.services;

import com.group.teona.entities.User;
import com.group.teona.enums.EnumRole;
import com.group.teona.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    
    @Override
    public User signUp (User user) {
    	user.setRole(EnumRole.User);
    	return userRepository.save(user);
	}

}
