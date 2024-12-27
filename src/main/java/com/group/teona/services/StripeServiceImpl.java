package com.group.teona.services;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl  implements StripeService{
	
    public StripeServiceImpl() {
    
        Stripe.apiKey = "sk_test_51QSEyz086BcD82hOxBsjZbrofuqD3EiYQDoRUNU0BIcbPfvrj6Bfy0u44sBgIJhaMYQ212riT1DoUap2OsZGeyVS00PjQCeIBg";
    }

    public String createPaymentIntent(Long amount) {
        try {
         
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(amount)  
                    .setCurrency("eur")
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return paymentIntent.getClientSecret();  
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
