package com.group.teona.register;


import java.util.Set;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegister {
	
	private User user;
    private Set<Adress> adresses;

}
