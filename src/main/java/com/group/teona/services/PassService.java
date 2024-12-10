package com.group.teona.services;

import com.group.teona.dto.FormTeonaPass;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;

public interface PassService {
	
	 Pass saveFormPass (FormTeonaPass formRequest, User user);


}
