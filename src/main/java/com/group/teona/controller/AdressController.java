package com.group.teona.controller;



import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.group.teona.services.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.security.JwtService;
import com.group.teona.services.PassService;
import com.group.teona.dto.FormTeonaPass;
import com.group.teona.dto.GetAdress;
import com.group.teona.dto.PassRequestDto;


@RestController
@RequestMapping("/api/adress")
public class AdressController {

	@Autowired
	private AdressService adressService;

	@Autowired
	private PassService passService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AdressRepository adressRepository;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/saveAddress")
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<?> saveForm(@RequestBody FormTeonaPass formRequest,
			@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
		try {
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
				String token = authorizationHeader.substring(7);

				if (token.split("\\.").length != 3) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Malformed JWT token"));
				}

				String usernameFromToken = jwtService.extractUsername(token);
				String emailFromToken = jwtService.extractEmail(token);

				User user = userRepository.findByEmail(usernameFromToken)
						.orElseThrow(() -> new IllegalArgumentException("User not found"));

				if (!jwtService.isTokenValid(token, user, emailFromToken)) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid token"));
				}

				Adress savedAddress = adressService.saveAddress (formRequest, user);
				return ResponseEntity.ok(Map.of("message", "Address saved successfully", "id", savedAddress.getId()

				));

			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}
	
	@GetMapping("/getUserAdress")
	public ResponseEntity getUserAdress (Authentication authentication) {
		
		if(authentication != null) {
			Optional<User> user = userRepository.findByEmail(authentication.getName());
	    	
			return ResponseEntity.ok(adressService.getUserAdress(user.get()));
		}
		
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist");

	}
	
	@GetMapping("/getAdress")
	public ResponseEntity getAdress (@RequestParam Long adressId) {
		
		Optional<Adress> adressOpt = adressRepository.findById(adressId);
		if (adressOpt != null) {
				
				Adress adress = adressOpt.get();
			
				GetAdress getAdress = new GetAdress();
				getAdress.setId(adress.getId());
				getAdress.setFirstName(adress.getFirstName());
				getAdress.setLastName(adress.getLastName());
				getAdress.setPhoneNumber(adress.getPhoneNumber());
				getAdress.setStreetName(adress.getStreetName());
				getAdress.setStreetNameOptional(adress.getStreetNameOptional());
				getAdress.setPostCode(adress.getPostCode());
				getAdress.setCity(adress.getCity());
				getAdress.setCountry(adress.getCountry());
				
				return ResponseEntity.ok(getAdress);
				
		}
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Adress doesn't exist");

	}

}
