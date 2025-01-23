package com.group.teona.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.group.teona.dto.ConfirmPaymentRequest;
import com.group.teona.dto.PaymentRequest;
import com.group.teona.services.StripeService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	 	@Autowired
	    private StripeService stripeService;

	    @PostMapping("/create-payment-intent")
	    public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody PaymentRequest paymentData) {
//	    	 try {
//	    		 PaymentRequest.CardDetails cardDetails = paymentData.getCardDetails();
//	    	        int amount = paymentData.getAmount().intValue();
//	    	        String currency = paymentData.getCurrency();
//
//	    	        Map<String, Object> params = new HashMap<>();
//	    	        params.put("type", "card");
//	    	        params.put("card", Map.of(
//	    	            "number", cardDetails.getNumber(),
//	    	            "exp_month", cardDetails.getExp_month(),
//	    	            "exp_year", cardDetails.getExp_year(),
//	    	            "cvc", cardDetails.getCvc()
//	    	        ));
//
//
//	    	        // Create PaymentMethod
//	    	        PaymentMethod paymentMethod = PaymentMethod.create(params);
//
//	    	        // Create PaymentIntent with the created PaymentMethod
//	    	        Map<String, Object> paymentIntentParams = new HashMap<>();
//	    	        paymentIntentParams.put("amount", amount); // Amount in cents
//	    	        paymentIntentParams.put("currency", currency);
//	    	        paymentIntentParams.put("payment_method", paymentMethod.getId());
//	    	        paymentIntentParams.put("confirmation_method", "automatic");
//
//	    	        PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentParams);
//
//	    	 
//	    	        Map<String, Object> response = new HashMap<>();
//	    	        response.put("payment_intent_id", paymentIntent.getId());
//	    	        response.put("client_secret", paymentIntent.getClientSecret());
//	    	        response.put("payment_method_id", paymentMethod.getId()); 
//
//	    	        return ResponseEntity.ok(response);
//	    	    } 
//	    	  catch (Exception e) {
//		            e.printStackTrace();
//		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
//		        }
	        
	    	 try {
	    		 PaymentIntent paymentIntent = stripeService.createPaymentIntent(paymentData.getAmount(), paymentData.getCurrency());
	    		 
	             Map<String, String> response = new HashMap<>();
	             response.put("client_secret", paymentIntent.getClientSecret());
	             response.put("payment_intent_id", paymentIntent.getId());
	             return ResponseEntity.ok().body(response);
	         } catch (Exception e) {
	             e.printStackTrace(); 
	             e.printStackTrace(); 
	        
	             Map<String, String> errorResponse = new HashMap<>();
	             errorResponse.put("error", "Failed to create payment intent: " + e.getMessage());
	             return ResponseEntity.badRequest().body(errorResponse);
	         }
	    }
	    @PostMapping("/confirm-payment")
	    public ResponseEntity<Map<String, Object>> confirmPayment(@RequestBody Map<String, Object> paymentData) {
	    	
	        try {
	            String paymentIntentId = (String) paymentData.get("paymentIntentId");
	            String paymentMethodId = (String) paymentData.get("PaymentMethodId");
	            	
	            if (paymentIntentId == null || paymentMethodId == null) {
	                throw new IllegalArgumentException("PaymentIntentId and PaymentMethodId are required.");
	            }

	            
	            boolean success = stripeService.confirmPayment(paymentIntentId, paymentMethodId);

	            Map<String, Object> response = new HashMap<>();
	            response.put("success", success);
	            return ResponseEntity.ok(response);
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
	        }
	    }
	    
	    
}
