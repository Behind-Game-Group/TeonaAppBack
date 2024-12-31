package com.group.teona.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/payment")
public class StripeWebhookController {
	
	@Value("${stripe.api.key}")
    private String stripeApiKey;

	
	@PostMapping("/webhook")
	public ResponseEntity<String> handleWebhook(HttpServletRequest request) {
	    String payload;
	    String sigHeader = request.getHeader(stripeApiKey);

	    try {
	        payload = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
	        Event event = Webhook.constructEvent(payload, sigHeader, stripeApiKey);

	        switch (event.getType()) {
	            case "payment_intent.succeeded":
	                PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer().getObject().get();
	                System.out.println("Payment succeeded: " + paymentIntent.getId());
	                // Update database with success
	                break;
	            case "payment_intent.payment_failed":
	                System.out.println("Payment failed");
	                break;
	        }

	        return ResponseEntity.ok("Webhook received");
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("Error processing webhook: " + e.getMessage());
	    }

}
}
