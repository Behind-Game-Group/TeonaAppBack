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
        SimpleMailMessage message = new SimpleMailMessage();
       try {
           System.out.println(toEmail+" T:T "+code);
           message.setTo(toEmail);
           message.setSubject("Your Verification Code");
           message.setText("Use the following verification code to complete your registration: " + code);
           mailSender.send(message);
       }catch (Exception e){
           System.out.println("une erreur interne est survenue ou l'email n'est pas reconnue"+e);
       }
       /*
       //spring.mail.properties.mail.smtp.connecttimeout=5000
//spring.mail.properties.mail.smtp.timeout=3000
spring.mail.properties.mail.smtp.writetimeout=5000

        */

    }
}
