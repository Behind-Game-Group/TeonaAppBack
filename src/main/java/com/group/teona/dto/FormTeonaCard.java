package com.group.teona.dto;


import com.group.teona.entities.Adress;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormTeonaCard {
	
	private boolean existingAdress;
	private Adress selectedAdress;
	
	private String firstName;
    private String lastName;
    private String streetName;
    private String streetNameOptional;
    private String postCode;
    private String city;
    private String phoneNumber;
    private String country;
    

}
