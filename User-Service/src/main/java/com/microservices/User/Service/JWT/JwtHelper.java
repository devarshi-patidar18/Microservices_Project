package com.microservices.User.Service.JWT;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {

    final String secretKey = "!@#$%^&*HFDTYH#$%CDDFFF?::KJHBVV&&^^&**((JJHJJJ))";

    public String generateToken(String username) {
        Map<String,Object> claims = new HashMap<>();
        return createClaim(claims,username);
    }
        
    private String createClaim(Map<String,Object> claims, String username) {
        return Jwts.builder()
                   .claims(claims)
                   .subject(username)
                   .header().empty().add("typ","jwt")
                   .and()
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis()+ 1000*60*10))
                   .signWith(signKey())
                   .compact();
    }

    private SecretKey signKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String extractUsername(String jwt) {
        Claims claims = extractAllClaims(jwt);
                return claims.getSubject();
            }
        
    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(signKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

}
