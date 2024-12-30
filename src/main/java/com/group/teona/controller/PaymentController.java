package com.group.teona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.group.teona.dto.PaymentRequest;
import com.group.teona.services.StripeService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	 	@Autowired
	    private StripeService stripeService;

	    @PostMapping("/create-payment-intent")
	    public ResponseEntity<?> createPaymentIntent(@RequestBody PaymentRequest paymentRequest) {
	        
	    	 try {
	             String clientSecret = stripeService.createPaymentIntent(
	                 paymentRequest.getAmount(), 
	                 paymentRequest.getCurrency()
	             );
	             return ResponseEntity.ok().body(clientSecret);
	         } catch (Exception e) {
	             e.printStackTrace(); 
	             return ResponseEntity
	                 .badRequest()
	                 .body("Failed to create payment intent: " + e.getMessage());
	         }
	    }

}
