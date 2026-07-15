package com.demo.studentservice.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.*;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secretBase64;

    @Value("${app.jwt.expiration-ms:1800000}")
    private long expirationMs;

    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretBase64);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username, List<String> roles) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validate(String token) {
        try {
            parser().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return parser().parseClaimsJws(token).getBody().getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token) {
        Object roles = parser().parseClaimsJws(token).getBody().get("roles");
        return roles == null ? Collections.emptyList() : (List<String>) roles;
    }

    private JwtParser parser() {
        return Jwts.parserBuilder().setSigningKey(key).build();
    }
}