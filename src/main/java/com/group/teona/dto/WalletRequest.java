package com.group.teona.dto;

import com.group.teona.entities.Pass;
import com.group.teona.entities.Wallet;

import lombok.Data;

@Data
public class WalletRequest {
	
	private Wallet wallet;
//	private Pass card;
	private Pass pass;

}

