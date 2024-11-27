package com.group.teona.dto;


import java.util.Set;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;

public class SignUpRequest {
	 private User user;
	 private Set<Adress> adress;

	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }

	    public Set<Adress> getAdress() {
	        return adress;
	    }

	    public void setAdress(Set<Adress> adress) {
	        this.adress = adress;
	    }
}
