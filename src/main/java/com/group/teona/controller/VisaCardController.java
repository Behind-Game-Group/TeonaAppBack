package com.group.teona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.dto.VisaCardRequest;
import com.group.teona.entities.VisaCard;
import com.group.teona.services.VisaCardService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/visacard")
public class VisaCardController {
	
	@Autowired
    private VisaCardService visaCardService;
	
	@GetMapping("/cards")
	public ResponseEntity<List<VisaCard>> getCards(@PathVariable Long walletId) {
	    List<VisaCard> cards = visaCardService.getCardsByWalletId(walletId);
	    return ResponseEntity.ok(cards);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addCard(
	        @RequestBody VisaCardRequest visaCardRequest) {
	
		VisaCard visaCard = visaCardService.addVisaCard(visaCardRequest.getWalletId(), visaCardRequest.getCardOwner(), visaCardRequest.getLastFourDigits());
	    return ResponseEntity.status(HttpStatus.CREATED).body(visaCard);
	}
	
	
	  @GetMapping("/{walletId}")
	    public ResponseEntity<List<VisaCard>> getVisaCards(@PathVariable Long walletId) {
	        List<VisaCard> visaCards = visaCardService.getVisaCardsByWalletId(walletId);
	        return ResponseEntity.ok(visaCards);
	    }
	  
	  @GetMapping("/user/{userId}")
	  public ResponseEntity<List<VisaCard>> getVisaCardsByUserId(@PathVariable Long userId) {
	      List<VisaCard> visaCards = visaCardService.getVisaCardsByUserId(userId);
	      return ResponseEntity.ok(visaCards);
	  }

}
