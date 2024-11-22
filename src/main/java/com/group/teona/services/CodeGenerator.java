package com.group.teona.services;

import org.springframework.stereotype.Service;

@Servic
public class CodeGenerator {
	  public static String generateVerificationCode() {
	        Random random = new Random();
	        return String.format("%06d", random.nextInt(1000000));
	    }
}
