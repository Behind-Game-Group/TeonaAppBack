package com.group.teona.dto;

import com.group.teona.entities.Adess;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;

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
	    private String userId;

	    private PassData passData;
	    
	    @Data
	    @NoArgsConstructor
	    public static class PassData {
	
	        private String cardTitle;
	        private Double cardPrice;
	        private boolean isActive;

	        
	    }
	
}
