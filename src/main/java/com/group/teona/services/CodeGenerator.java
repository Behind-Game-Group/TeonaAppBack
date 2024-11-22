package com.group.teona.services;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class CodeGenerator {
	  public static String generateVerificationCode() {
	        Random random = new Random();
	        return String.format("%06d", random.nextInt(1000000));
	    }
}
