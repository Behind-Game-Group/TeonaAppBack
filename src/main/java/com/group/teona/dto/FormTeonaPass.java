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
public class FormTeonaPass extends FormTeonaCard {
	private String image;
	    private EnumSub subscriptionTime;
	    

	 
}
