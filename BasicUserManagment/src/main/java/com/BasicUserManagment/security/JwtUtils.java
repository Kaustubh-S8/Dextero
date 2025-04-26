package com.BasicUserManagment.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Base64;
@Component
public class JwtUtils {
	 private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	 
	 	@Value("${app.jwtSecret}")
	    private String jwtSecret;
	    
	    @Value("${app.jwtExpirationMs}")
	    private int jwtExpirationMs;
	    @PostConstruct
	    public void validateKey() {
	    	try {
	            getSigningKey(); // Will throw if invalid
	        } catch (Exception e) {
	            throw new IllegalStateException("""
	                Current key: """ + jwtSecret);
	        }
	    }
	    
	    private Key getSigningKey() {
	    	 String cleanKey = jwtSecret.replaceAll("[^a-zA-Z0-9+/=]", "");
	         byte[] keyBytes = Base64.getDecoder().decode(cleanKey);
	         return Keys.hmacShaKeyFor(keyBytes);
	        
	    }
	    
	    

	    public String generateJwtToken(Authentication authentication) {
	        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
	        List<String> roles = userPrincipal.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.toList());

	        Claims claims = Jwts.claims().setSubject(userPrincipal.getUsername());
	        claims.put("roles", roles);
	        
	        return Jwts.builder()
	                .setSubject(userPrincipal.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
	                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
	                .compact();
	    }
	     public boolean validateToken(String token) {
	         try {
	             Jwts.parserBuilder()
	                 .setSigningKey(getSigningKey())
	                 .build()
	                 .parseClaimsJws(token);
	             return true;
	         } catch (Exception e) {
	             return false;
	         }
	     }
	    public String getUserNameFromJwtToken(String token) {
	        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	    }

		public boolean validateJwtToken(String jwt) {
			 try {
		            Jwts.parserBuilder()
		                .setSigningKey(getSigningKey())
		                .build()
		                .parseClaimsJws(jwt);
		            return true;
		        } catch (Exception e) {
		            return false;
		        }
		}

	
	   
}