package com.group.teona.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail, String code) {

    	   try {
               SimpleMailMessage message = new SimpleMailMessage();
               message.setTo(toEmail);
               message.setSubject("Your Verification Code");
               message.setText("Use the following verification code to complete your registration: " + code);
               mailSender.send(message);
               System.out.println("Verification email sent successfully to " + toEmail);
           } catch (Exception e) {
               System.err.println("Failed to send email to " + toEmail);
               e.printStackTrace();
           }

    }
}
