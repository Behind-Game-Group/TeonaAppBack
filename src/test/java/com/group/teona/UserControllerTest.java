package com.group.teona;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.group.teona.controller.UserController;
import com.group.teona.dto.forgotPasswordRequest;
import com.group.teona.dto.resetPasswordRequest;
import com.group.teona.entities.User;
import com.group.teona.services.EmailService;
import com.group.teona.services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	    @InjectMocks
	    private UserController userController; 

	    @Mock
	    private UserService userService;

	    @Mock
	    private EmailService emailService;
	    
	    @Mock
	    PasswordEncoder passwordEncoder;

	    @Test
	    void testForgotPassword_Success() {
	        forgotPasswordRequest request = new forgotPasswordRequest();
	        request.setEmail("test@example.com");

	        User mockUser = new User();
	        mockUser.setEmail(request.getEmail());

	        when(userService.findByEmail("test@example.com")).thenReturn(mockUser);
	        when(emailService.sendPasswordResetEmail(anyString(), anyString())).thenReturn(true);

	        // Act
	        ResponseEntity<?> response = userController.forgotPassword(request);

	        // Assert
	        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	        Assertions.assertEquals("Password reset email sent successfully.", response.getBody());
	    }

	    @Test
	    void testForgotPassword_EmailNotFound() {
	    	forgotPasswordRequest request = new forgotPasswordRequest();
	        request.setEmail("unknown@example.com");

	        when(userService.findByEmail(request.getEmail())).thenThrow(new IllegalArgumentException("User not found"));

	        // Act: Call the controller method
	        ResponseEntity<?> response = userController.forgotPassword(request);

	        // Assert: Verify that the response indicates the user was not found
	        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	        Assertions.assertEquals("Error while processing the request.", response.getBody());
	    }

	    @Test
	    void testForgotPassword_EmailIsNull() {
	    	 forgotPasswordRequest request = new forgotPasswordRequest();
	    	 
	    	    request.setEmail(null);

	    	
	    	    ResponseEntity<?> response = userController.forgotPassword(request);

	        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        Assertions.assertEquals("Email is required.", response.getBody());
	    }
	    
	    
	    
	    
	    @Test
	    void testForgotPassword_ValidEmail() {
	        forgotPasswordRequest request = new forgotPasswordRequest();
	        request.setEmail("malshis@yahoo.com");

	        User user = new User();
	        user.setEmail("malshis@yahoo.com");

	        Mockito.when(userService.findByEmail(request.getEmail())).thenReturn(user);
	        Mockito.when(emailService.sendPasswordResetEmail(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

	        ResponseEntity<?> response = userController.forgotPassword(request);

	        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	        Assertions.assertEquals("Password reset email sent successfully.", response.getBody());
	    }
	    
	    @Test
	    void testResetPassword_ValidToken() {
	        resetPasswordRequest request = new resetPasswordRequest();
	        request.setToken("valid-token");
	        request.setNewPassword("newSecurePassword");

	        User user = new User();
	        user.setResetToken("valid-token");
	        user.setTokenExpirationTime(LocalDateTime.now().plusMinutes(10));

	        Mockito.when(userService.findByResetToken(request.getToken())).thenReturn(user);
	        Mockito.when(passwordEncoder.encode(request.getNewPassword())).thenReturn("hashedPassword");

	        ResponseEntity<?> response = userController.resetPassword(request);

	        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	        Assertions.assertEquals("Password has been reset successfully.", response.getBody());
	        Mockito.verify(userService, Mockito.times(1)).updateUser(Mockito.any(User.class));
	    }

}
