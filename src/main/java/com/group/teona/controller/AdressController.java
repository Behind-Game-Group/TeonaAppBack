package com.group.teona.controller;



import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;



import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.UserRepository;
import com.group.teona.security.JwtService;
import com.group.teona.services.PassService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.MalformedURLException;

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

	private static final String UPLOAD_DIR = "uploads/";
	
	@PostMapping("/saveAddress")
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<?> saveForm(
	        @RequestPart("formRequest") FormTeonaPass formRequest,
	        @RequestPart(value = "image", required = false) MultipartFile image,  
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

	           
	            String imageName = null;
	            if (image != null && !image.isEmpty()) {
	                Path uploadDir = Paths.get("uploads");
	                if (!Files.exists(uploadDir)) {
	                    Files.createDirectories(uploadDir); 
	                }

	                imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename(); 
	                Path filePath = uploadDir.resolve(imageName);
	                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	            }

	            
	            formRequest.setImage(imageName);  
	            Adress updatedAddress = adressService.saveOrUpdateAddress(formRequest, user);

	            return ResponseEntity.ok(Map.of(
	                    "message", "Address saved or updated successfully",
	                    "id", updatedAddress.getId(),
	                    "image", imageName
	            ));
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
	    }
	}
	
//	public ResponseEntity<?> saveForm(@RequestBody FormTeonaPass formRequest,
//			@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
//		try {
//			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
//				String token = authorizationHeader.substring(7);
//
//				if (token.split("\\.").length != 3) {
//					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Malformed JWT token"));
//				}
//
//				String usernameFromToken = jwtService.extractUsername(token);
//				String emailFromToken = jwtService.extractEmail(token);
//
//				User user = userRepository.findByEmail(usernameFromToken)
//						.orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//				if (!jwtService.isTokenValid(token, user, emailFromToken)) {
//					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid token"));
//				}
//				Adress updatedAddress = adressService.saveOrUpdateAddress(formRequest, user);
//
//	            return ResponseEntity.ok(Map.of(
//	                "message", "Address saved or updated successfully",
//	                "id", updatedAddress.getId()
//	            ));
//
//
//			}
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
//		}
//	}
	
	
//	 @GetMapping("/uploads/{filename:.+}")
//	    public ResponseEntity<String> getImage(@RequestParam("file") MultipartFile file) {
//		 try {
//			 
//			  Path uploadDir = Paths.get("uploads");
//		        if (!Files.exists(uploadDir)) {
//		            Files.createDirectories(uploadDir);
//		        }
//		        
//	            Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
//	            Resource resource = new UrlResource(filePath.toUri());
//
//	            if (!resource.exists() || !resource.isReadable()) {
//	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//	                        .body(null);
//	            }
//
//	          
//	            String contentType = Files.probeContentType(filePath);
//	            if (contentType == null) {
//	                contentType = "application/octet-stream";
//	            }
//
//	            return ResponseEntity.ok()
//	                    .contentType(MediaType.parseMediaType(contentType))
//	                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
//	                    .body(resource);
//	        } catch (MalformedURLException e) {
//	            return ResponseEntity.badRequest().body(null);
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//	        }
//	 }
//	       try {
//	           
//	            Path path = Paths.get("uploads").resolve(filename);
//	            Resource file = new UrlResource(path.toUri());
//
//	            
//	            if (file.exists() || file.isReadable()) {
//	                return ResponseEntity.ok()
//	                        .contentType(MediaType.IMAGE_JPEG) 
//	                        .body(file);
//	            } else {
//	                return ResponseEntity.notFound().build();
//	            }
//	            
//	        } catch (MalformedURLException e) {
//	            
//	            return ResponseEntity.badRequest().body(null); 
//	        } catch (Exception e) {
//	          
//	            return ResponseEntity.internalServerError().build();
//	        }
	   
	    
	 
	
	@GetMapping("/getAdress")
	public ResponseEntity<?> getAddress(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
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

	          
	            Optional<Adress> existingAddress = adressRepository.findByUserId(user.getId());
	            
	            if (existingAddress.isPresent()) {
	            	 Adress address = existingAddress.get();
	            	    System.out.println("Address found: " + address);
	            	    System.out.println("Address image: " + address.getImage());
	                return ResponseEntity.ok(existingAddress.get());
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No address found for user"));
	            }
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
	        
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
	    }
	}

}
