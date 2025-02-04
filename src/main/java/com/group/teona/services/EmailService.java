package com.group.teona.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.group.teona.entities.Adress;
import com.group.teona.repositories.AdressRepository;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private AdressRepository adressRepository;
    

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
            String resetLink = "http://localhost:8081/hub/ResetPassword?token=" + resetToken;
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
    
    public boolean sendInvoiceEmail(String toEmail, String cardTitle, double price, String adressId) {
        try {
        	
        	Optional<Adress> addressOptional = adressRepository.findById(Long.parseLong(adressId));
        	
            Adress address = addressOptional.get();

            // Extract user details from address
            String firstName = address.getFirstName();
            String lastName = address.getLastName();
            String fullAddress = address.getStreetName() + ", " + 
                                 (address.getStreetNameOptional() != null ? address.getStreetNameOptional() + ", " : "") + 
                                 address.getPostCode() + " " + address.getCity() + ", " + address.getCountry();

            
            
        	
        	SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Your Payment Invoice");
            message.setText("Dear " + firstName + " " + lastName + ",\n\n" + "Thank you for your payment.\n\n" +
                            "Invoice Details:\n" +
                            "---------------------------------\n" +
                            "Card: " + cardTitle + "\n" +
                            "Amount Paid: " + price + " €\n" +
                            "Billing Address: \n" + fullAddress + "\n\n" +
                            "Thank you for using our service!");

            mailSender.send(message);
            System.out.println("Invoice email sent successfully to " + toEmail);
            return true;
        } catch (Exception e) {
            System.err.println("Failed to send invoice email to " + toEmail);
            e.printStackTrace();
            return false;
        }
    }

}
