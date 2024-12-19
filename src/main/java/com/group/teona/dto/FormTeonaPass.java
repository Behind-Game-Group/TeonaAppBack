package com.group.teona.dto;

import com.group.teona.enums.EnumSub;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class FormTeonaPass {
	 private String firstName;
	    private String lastName;
	    private String streetName;
	    private String streetNameOptional;
	    private String postCode;
	    private String city;
	    private String phoneNumber;
	    private String country;
	    private String image;
	    private EnumSub subscriptionTime;
	    

	 
}
