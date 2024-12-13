package com.group.teona.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group.teona.dto.FormTeonaPass;
import com.group.teona.dto.PassRequestDto;
import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;

@Service
public interface PassService {
	 void savePass(PassRequestDto passRequest, User user,Long adressId,Wallet wallet);
}
