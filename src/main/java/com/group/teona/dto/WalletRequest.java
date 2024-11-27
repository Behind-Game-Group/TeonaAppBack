package com.group.teona.dto;

import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.Wallet;

import lombok.Data;

@Data
public class WalletRequest {
	
	private Wallet wallet;
	private Card card;
	private Pass pass;

}
