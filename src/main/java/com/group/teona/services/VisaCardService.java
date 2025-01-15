package com.group.teona.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group.teona.entities.VisaCard;

@Service
public interface VisaCardService {

	public List<VisaCard> getCardsByWalletId(Long walletId);
	public VisaCard addVisaCard(Long walletId, String cardOwner, String lastFourDigits);
	public List<VisaCard> getVisaCardsByWalletId(Long walletId);
}
