package com.security;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.Objects;

import org.springframework.util.ObjectUtils;

import io.jsonwebtoken.Claims;

@Component
public class JWTSecurity {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;
    public String generateTolken(String username){
        SecretKey key = gSecretKey();
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
            .signWith(key)
            .compact();

    }

    private SecretKey gSecretKey(){
        SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());
        return key;
    }
    public Boolean isValidTolken(String tolken){
        Claims claims = getClaims(tolken);
        if(Objects.nonNull(claims)){
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if(ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(expirationDate) || now.after(expirationDate))
                return true;
        }
        return false;
    }
    public Claims getClaims(String tolken){
        SecretKey key = gSecretKey();
        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(tolken)
                .getBody();
        } catch (Exception e) {
            return null;
        }}
}