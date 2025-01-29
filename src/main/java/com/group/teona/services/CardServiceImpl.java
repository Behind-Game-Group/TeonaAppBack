package com.group.teona.services;


import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.dto.FormTeonaCard;
import com.group.teona.dto.PassRequestDto;
import com.group.teona.dto.CardRequestDto;
import com.group.teona.dto.ChoiceTopUp;
import com.group.teona.entities.Adress;
import com.group.teona.entities.Card;
import com.group.teona.entities.Pass;
import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.CardRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.repositories.WalletRepository;
import com.stripe.exception.StripeException;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    CardRepository cardRepository;
	
	@Autowired
    WalletRepository walletRepository;
	
	 @Autowired
	    private StripeService stripeService;
	 
	 @Autowired
	    private AdressRepository adressRepository;
	 
	
	@Override
    public void saveFormCardWithUser ( CardRequestDto cardRequest,  User user,Long adressId, String paymentIntentId, String paymentMethodId) {
		String paymentStatus = null;
  	  try {
	       
	         paymentStatus = stripeService.getPaymentStatus(paymentIntentId);
	        
	         System.out.println("Payment Status: " + paymentStatus);

 	        switch (paymentStatus) {
 	            case "succeeded":
 	                System.out.println("Payment confirmed. Proceeding to save the pass.");
 	                break;

 	            case "requires_payment_method":
 	                throw new IllegalStateException("Payment failed: Invalid payment method or card declined.");

 	            case "requires_action":
 	                throw new IllegalStateException("Payment requires user authentication. Please complete authentication.");

 	            case "processing":
 	                throw new IllegalStateException("Payment is still processing. Please wait and try again.");

 	            case "canceled":
 	                throw new IllegalStateException("Payment was canceled by the user.");

 	            case "requires_capture":
 	                throw new IllegalStateException("Payment needs manual capture. Please capture the payment in Stripe.");

 	            default:
 	                throw new IllegalStateException("Unknown payment status: " + paymentStatus);
 	        }
	  }
	 catch (StripeException e) {
       throw new IllegalStateException("Error while confirming payment", e);
   }

	System.out.println("cardRequest: " + cardRequest);
  System.out.println("User: " + user);
  System.out.println("AdressId: " + adressId);
	 if (cardRequest == null || user == null || adressId == null ) {
       throw new IllegalArgumentException("Invalid input: CardRequest, User, Address ID");
   }

	  Optional<Adress> optionalAdress = adressRepository.findById(adressId);

    if (optionalAdress.isEmpty()) {
        throw new IllegalArgumentException("Address with ID " + adressId + " not found");
    }
    Adress adress = optionalAdress.get();
	   
    
    Card existingInactiveCard = cardRepository.findFirstByWalletAndIsActive(user.getWallet(), false);
    Wallet wallet;
    if (existingInactiveCard != null) {
        wallet = existingInactiveCard.getWallet();
    } else {
   
        wallet = user.getWallet();
        if (wallet == null) {
            wallet = new Wallet();
            wallet.setUser(user);
            wallet.setPhoneNumber(user.getPhoneNumber());
            walletRepository.save(wallet);
            user.setWallet(wallet);
        }
    

     }
    
     Card card = new Card();
  card.setDateSubscription(LocalDate.now());
  card.setCardPrice(cardRequest.getCardPrice());
  card.setCardTitle(cardRequest.getCardTitle());
  card.setActive("succeeded".equals(paymentStatus));
  card.setAdress(adress);
  card.setWallet(wallet);
  card.setUser(user);
  card.setPaymentStatus(paymentStatus); 
  

  cardRepository.save(card);

    }

//	
//	@Override
//	public Long saveFormCardWithoutUser (FormTeonaCard formRequest) {
//		
//		Adress adress = new Adress();
//		adress.setFirstName(formRequest.getFirstName());
//		adress.setLastName(formRequest.getLastName());
//		adress.setCountryCode(formRequest.getCountryCode());
//		adress.setStreetName(formRequest.getStreetName());
//		adress.setStreetNameOptional(formRequest.getStreetNameOptional());
//		adress.setPostCode(formRequest.getPostCode());
//		adress.setCity(formRequest.getCity());
//		adress.setCountry(formRequest.getCountry());
//
//		adressRepository.save(adress);
//		
//		Card teonaCard = new Card();
//		teonaCard.setActive(true);
//		teonaCard.setAdress(adress);
//		teonaCard.setTopUp(0.0);
//		
//		
//		Wallet wallet = new Wallet();
//		walletRepository.save(wallet);
//		
//
//		teonaCard.setWallet(wallet);
//		
//		cardRepository.save(teonaCard);
//		
//		return teonaCard.getId();
//
//				
//		
//	}
	
//	@Override
//	public Card addTopUp (Long cardId, ChoiceTopUp formTopUp) {
//		Optional<Card> card = cardRepository.findById(cardId);
//		Card cardFind = card.get();
//
//		if (cardFind != null) {
//			if(formTopUp.isTopUp5()) {
//				cardFind.setTopUp(cardFind.getTopUp() + 5.0);
//			}
//			if(formTopUp.isTopUp10()) {
//				cardFind.setTopUp(cardFind.getTopUp() + 10.0);
//			}
//			if(formTopUp.isTopUp15()) {
//				cardFind.setTopUp(cardFind.getTopUp() + 15.0);
//			}
//			if(formTopUp.isTopUp20()) {
//				cardFind.setTopUp(cardFind.getTopUp() + 20.0);
//			}
//			if(formTopUp.getTopUpPerso() > 0 ) {
//				cardFind.setTopUp(cardFind.getTopUp() + formTopUp.getTopUpPerso());
//			}
//
//			if(cardFind.getTopUp() > 0) {
//
//				cardFind.setActive(true);
//				return cardRepository.save(cardFind);
//			}
//
//		}
//		throw new IllegalArgumentException("Arguements non valides");
//	}
}



