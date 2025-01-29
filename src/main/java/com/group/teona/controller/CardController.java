package com.group.teona.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.teona.dto.CardRequestDto;
import com.group.teona.dto.FormTeonaCard;
import com.group.teona.dto.FormTopUp;
import com.group.teona.dto.PassRequestDto;
import com.group.teona.entities.User;
import com.group.teona.repositories.UserRepository;
import com.group.teona.repositories.WalletRepository;
import com.group.teona.security.JwtService;
import com.group.teona.services.CardService;
import com.group.teona.services.StripeService;

@RestController
@RequestMapping("api/add")
public class CardController {

	@Autowired
	private CardService cardService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

		@Autowired
	WalletRepository walletRepository;
	
	@Autowired
	UserDetailsService userDetailsService;

	@PostMapping("/saveCard")
	@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
	public ResponseEntity<?> savePass(@RequestBody CardRequestDto cardRequest,
			@RequestHeader(value = "Authorization") String authorizationHeader,
			@RequestParam String paymentIntentId,
			@RequestParam("paymentMethodId") String paymentMethodId) {
		try {
			System.out.println("Received Authorization Header: " + authorizationHeader);
			System.out.println("Received CardRequest: " + cardRequest);
			System.out.println("Payment Intent ID: " + paymentIntentId);

			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
				String token = authorizationHeader.substring(7);

				if (token.split("\\.").length != 3) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Malformed JWT token");
				}

				String usernameFromToken = jwtService.extractUsername(token);
				String emailFromToken = jwtService.extractEmail(token);
				UserDetails userDetails = userDetailsService.loadUserByUsername(usernameFromToken);

				if (jwtService.isTokenValid(token, userDetails, emailFromToken)) {
					User user = userRepository.findByEmail(usernameFromToken)
							.orElseThrow(() -> new IllegalArgumentException("User not found"));

					Long adressId = cardRequest.getAdressId();

					cardService.saveFormCardWithUser(cardRequest, user, adressId, paymentIntentId, paymentMethodId);

					Map<String, Object> response = new HashMap<>();
					response.put("message", "Card saved successfully");
					response.put("cardPrice", user.getWallet().getCount());

					return ResponseEntity.ok(response);
				} else {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
				}
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Authorization header must start with 'Bearer '");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Card: " + e.getMessage());
		}

	}
//
//	@PutMapping("card/topUp")
//	public ResponseEntity saveFormWithCard(@RequestBody FormTopUp formTopUp) {
//		try {
//			cardService.addTopUp(formTopUp.getCardId(), formTopUp.getChoiceTopUp());
//			return ResponseEntity.ok("Top-up added");
//
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Add a valid top-up" + e);
//		}
//
//	}

}
