package com.group.teona.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    final static private SignatureAlgorithm alg = SignatureAlgorithm.HS256;
    private final static SecretKey SECRET_KEY = generateSecretKey();

    private static SecretKey generateSecretKey() {
        return Keys.secretKeyFor(alg);
    }

    public String generateToken (UserDetails userDetails,String email, Long userId){
    	 Map<String, Object> claims = new HashMap<>();
         claims.put("userId", userId);
         claims.put("email", email);
         return generateToken(claims, userDetails.getUsername());
    }

    public String generateToken (Map<String, Object> extraClaims, String subject){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 5))
                .claim("email", extraClaims.get("email"))
                .signWith(SECRET_KEY, alg)
                .compact();
    }


    public boolean isTokenValid(String token, UserDetails userDetails,String email) {
        final String usernameFromToken = extractUsername(token);
        final String emailFromToken = extractEmail(token);
//        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        return usernameFromToken.equals(userDetails.getUsername()) 
                && emailFromToken.equals(email) 
                && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }
    public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);

    }
    public boolean isTokenValid(String token) {
        try {
            String username = extractUsername(token);
            return username != null && !isTokenExpired(token);
        } catch (Exception e) {
            return false; 
        }
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
