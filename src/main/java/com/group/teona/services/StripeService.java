package com.group.teona.services;

import org.springframework.stereotype.Service;

@Service
public interface StripeService {
	  public String createPaymentIntent(Long amount);
}
