package com.group.teona.services;

import com.group.teona.dto.FormTeonaPass;
import com.group.teona.entities.User;

public interface PassService {
	
	 void saveFormPass (FormTeonaPass formRequest, User user);


}
