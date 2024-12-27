package com.group.teona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.dto.PaymentRequest;
import com.group.teona.services.StripeService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	 	@Autowired
	    private StripeService stripeService;

	    @PostMapping("/create-payment-intent")
	    public String createPaymentIntent(@RequestBody PaymentRequest paymentRequest) {
	        
	        return stripeService.createPaymentIntent(paymentRequest.getAmount());
	    }

}
