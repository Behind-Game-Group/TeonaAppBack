package com.group.teona.dto;

import lombok.Data;

@Data
public class GetAdress {
	
	private Long id;

    private String firstName;

    private String lastName;

    private String streetName;

    private String streetNameOptional;

    private String postCode;

    private String city;

    private String countryCode;

    private String country;   
    

}
