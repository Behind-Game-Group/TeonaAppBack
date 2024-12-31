package com.group.teona.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChoiceTopUp {
	private Long cardId;
	private boolean topUp5 ;
	private boolean topUp10 ;
	private boolean topUp15 ;
	private boolean topUp20 ;
	private double topUpPerso;

	
	

}
