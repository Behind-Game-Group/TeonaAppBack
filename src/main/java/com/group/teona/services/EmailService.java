package com.group.teona.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendVerificationEmail(String toEmail, String code) {

    	   try {
               SimpleMailMessage message = new SimpleMailMessage();
               message.setTo(toEmail);
               message.setSubject("Your Verification Code");
               message.setText("Use the following verification code to complete your registration: " + code);
               mailSender.send(message);
               System.out.println("Verification email sent successfully to " + toEmail);
               return true;
           } catch (Exception e) {
               System.err.println("Failed to send email to " + toEmail);
               e.printStackTrace();
               return false;
           }

    }
    
    public boolean sendPasswordResetEmail(String toEmail, String resetToken) {
        try {
            String resetLink = "http://localhost:8081/api/user/reset-password?token=" + resetToken;
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Password Reset Request");
            message.setText("Click the link below to reset your password:\n" + resetLink + 
                            "\n\nIf you did not request a password reset, please ignore this email.");
            mailSender.send(message);
            System.out.println("Password reset email sent successfully to " + toEmail);
            return true;
        } catch (Exception e) {
            System.err.println("Failed to send password reset email to " + toEmail);
            e.printStackTrace();
            return false;
        }
    }
    

}
