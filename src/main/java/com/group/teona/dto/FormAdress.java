package com.group.teona.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormAdress {
		
    	private String number;
	    private String streetName;
	    private String streetNameOptional;
	    private String postCode;
	    private String city;
	    private String country;

}
