package com.dkowalczyk.real_time_chat_app.security.jwt;


import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    public String generateToken(UserEntity user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("email", user.getEmail())
                .claim("username", user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshToken(UserEntity user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshExpiration);

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("email", String.class);
    }

    public boolean validateRefreshToken(String refreshToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(refreshToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getEmailFromRefreshToken(String refreshToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            log.debug("Starting token validation for token: {}", token.substring(0, 10) + "...");

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            log.debug("Token claims: {}", claims);

            // Sprawdź datę wygaśnięcia
            Date expiration = claims.getExpiration();
            Date now = new Date();
            log.debug("Token expiration: {}, current time: {}", expiration, now);

            return !expiration.before(now);
        } catch (SecurityException e) {
            log.error("Invalid JWT signature", e);
            return false;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token", e);
            return false;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token", e);
            return false;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token", e);
            return false;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty", e);
            return false;
        } catch (Exception e) {
            log.error("Unexpected error during token validation", e);
            return false;
        }
    }

    private Key getSigningKey() {
        try {
            log.debug("Getting signing key with secret length: {}", jwtSecret.length());
            byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            log.error("Error creating signing key", e);
            throw e;
        }
    }
}
