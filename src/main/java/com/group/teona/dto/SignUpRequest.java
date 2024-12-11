package com.group.teona.dto;


import java.util.Set;

import com.group.teona.entities.Adess;
import com.group.teona.entities.User;

import lombok.Data;

@Data
public class SignUpRequest {
	 private User user;

	  public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }


	   
}
