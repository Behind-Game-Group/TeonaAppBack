package com.group.teona.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;


import com.group.teona.entities.User; 
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import javax.crypto.SecretKey;


@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtil {
	
	  final static private SignatureAlgorithm alg = SignatureAlgorithm.HS256;
	   private final static SecretKey SECRET_KEY = loadOrGenerateSecretKey();

	    private static SecretKey loadOrGenerateSecretKey() {
	        try {
	            // Check if a key exists in a file
	            Path path = Paths.get("secret.key");
	            if (Files.exists(path)) {
	                byte[] keyBytes = Files.readAllBytes(path);
	                return Keys.hmacShaKeyFor(keyBytes);
	            } else {
	                // Generate and save the key
	                SecretKey key = Keys.secretKeyFor(alg);
	                Files.write(path, key.getEncoded());
	                return key;
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("Could not load or generate secret key", e);
	        }
	    }
//	    private final static SecretKey SECRET_KEY = generateSecretKey();
//
//	    private static SecretKey generateSecretKey() {
//	        return Keys.secretKeyFor(alg);
//	    }


	    public String generateToken(UserDetails userDetails) {
	        Map<String, Object> claims = new HashMap<>();
	        claims.put("nickname", ((User) userDetails).getFirstName());
	        claims.put("id", ((User) userDetails).getId());
	        claims.put("roles", ((User) userDetails).getRole());
	        return generateToken(claims, userDetails.getUsername());
	    }
    
	    public String generateToken (Map<String, Object> extraClaims, String subject){
	        return Jwts
	                .builder()
	                .setClaims(extraClaims)
	                .setSubject(subject)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 5))
	                .signWith(SECRET_KEY, alg)
	                .compact();
	    }


	    public boolean isTokenValid(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	    }

	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);

	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts
	                .parserBuilder()
	                .setSigningKey(SECRET_KEY)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }
}

