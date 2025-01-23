package com.group.teona.services;

import java.util.Map;

import org.springframework.stereotype.Service;
import com.group.teona.dto.ConfirmPaymentRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Service
public interface StripeService {
	  public PaymentIntent createPaymentIntent(Long amount, String currency) throws StripeException;
	  public boolean confirmPayment(String paymentIntentId ,String paymentMethodId) throws StripeException;
}
