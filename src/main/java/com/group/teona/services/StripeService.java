package com.group.teona.services;

import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;

@Service
public interface StripeService {
	  public String createPaymentIntent(Long amount, String currency) throws StripeException;
}
