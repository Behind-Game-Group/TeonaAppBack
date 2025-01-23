package com.group.teona.services;

import com.group.teona.dto.ConfirmPaymentRequest;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentIntentUpdateParams;
import com.stripe.param.PaymentMethodCreateParams;

import jakarta.annotation.PostConstruct;

import com.stripe.exception.*;

import org.springframework.stereotype.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

@Service
public class StripeServiceImpl  implements StripeService{
	
	@Value("${stripe.api.key}")
    private String stripeApiKey;
	
	 @PostConstruct
	    public void init() {
	        Stripe.apiKey = stripeApiKey;
	    }
	 

    public PaymentIntent createPaymentIntent(Long amount, String currency)  throws StripeException{
    	   Stripe.apiKey = stripeApiKey;

           PaymentIntentCreateParams params =
               PaymentIntentCreateParams.builder()
                   .setAmount(amount) 
                   .setCurrency(currency)                   
                   .build();

           return PaymentIntent.create(params);
       }

    public boolean confirmPayment(String paymentIntentId , String paymentMethodId) throws StripeException {
    	
    	Stripe.apiKey = stripeApiKey;
    	

    	
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        
        System.out.println("Payment Intent (Before Confirming): " + paymentIntent);
        
        if (paymentIntent.getPaymentMethod() == null) {
            PaymentIntentUpdateParams updateParams = PaymentIntentUpdateParams.builder()
                    .setPaymentMethod(paymentMethodId)  
                    .build();
            paymentIntent = paymentIntent.update(updateParams);
            System.out.println("Updated Payment Intent (After Attaching Payment Method): " + paymentIntent);
        }

        
        PaymentIntentConfirmParams confirmParams = PaymentIntentConfirmParams.builder()
                .setPaymentMethod(paymentMethodId)
                .build();
        PaymentIntent confirmedPaymentIntent = paymentIntent.confirm(confirmParams);

        System.out.println("Confirmed Payment Intent: " + confirmedPaymentIntent);

        return "succeeded".equals(confirmedPaymentIntent.getStatus());
        
      
      }

	
    
}
