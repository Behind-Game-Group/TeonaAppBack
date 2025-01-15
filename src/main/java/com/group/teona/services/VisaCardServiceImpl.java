package com.group.teona.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.entities.VisaCard;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.VisaCardRepository;
import com.group.teona.repositories.WalletRepository;

@Service
public class VisaCardServiceImpl implements VisaCardService{

	
	@Autowired
    private VisaCardRepository visacardRepository;

    @Autowired
    private WalletRepository walletRepository;
    
	@Override
	public List<VisaCard> getCardsByWalletId(Long walletId) {
		return visacardRepository.findByWalletId(walletId);
	}

	@Override
	public VisaCard addVisaCard(Long walletId, String cardOwner, String lastFourDigits) {
	      Wallet wallet = walletRepository.findById(walletId)
	                .orElseThrow(() -> new RuntimeException("Wallet not found"));

	        VisaCard card = new VisaCard();
	        card.setCardOwner(cardOwner);
	        card.setLastFourDigits(lastFourDigits);
	        card.setWallet(wallet);

	        return visacardRepository.save(card);
	}
	
	@Override
	public List<VisaCard> getVisaCardsByWalletId(Long walletId) {
	    Wallet wallet = walletRepository.findById(walletId)
	                .orElseThrow(() -> new RuntimeException("Wallet not found"));

	    return visacardRepository.findByWallet(wallet);
	}

}
