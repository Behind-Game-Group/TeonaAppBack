package com.group.teona.services;


import org.springframework.stereotype.Service;

import com.group.teona.dto.PassRequestDto;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;

@Service
public interface PassService {
	 void savePass(PassRequestDto passRequest, User user,Long adressId);
}
