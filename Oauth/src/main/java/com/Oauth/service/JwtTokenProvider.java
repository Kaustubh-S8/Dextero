package com.Oauth.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProvider {

	@Value("${app.jwtSecret}")
    private String jwtSecret;
    
    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;
    
    
    private Key key() {
    	return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    public String generateToken(Authentication authentication) {
    	String username=authentication.getName();
    	Date now=new Date();
    	Date expiryDate =new Date(now.getTime()+jwtExpirationMs);
    	
    	return Jwts.builder().setSubject(username)
    						.setIssuedAt(now)
    						.setExpiration(expiryDate)
    						.signWith(key())
    						.compact();
    	
    }
    
    public String getUsernameFromToken(String token) {
    	Claims claims=Jwts.parserBuilder()
    						.setSigningKey(key())
    						.build()
    						.parseClaimsJws(token)
    						.getBody();
    	return claims.getSubject();
    }
    
    public boolean validateToken(String token) {
    	try {
    		Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
    		return true;
    	}catch (Exception e) {
            return false;
        }
    }
}
