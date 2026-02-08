package org.letsplay.letsplay.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.letsplay.letsplay.Exceptions.ApiException;
import org.letsplay.letsplay.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.letsplay.letsplay.config.jwtConfig;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {
    private jwtConfig jwtConfig;
    private UserRepository userRepository;
    public String generateToken(UUID userUuid) {
        if (userUuid == null) {
            return null;
        }
        if (jwtConfig.getSecret() == null || jwtConfig.getSecret().length() < 32) {
            throw new IllegalArgumentException("Secret is null or empty");
        }
        Key tokenKey = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(userUuid.toString())
                .setIssuedAt(new Date())
                .signWith(tokenKey)
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .compact();
    }

    public Claims parseToken(String token) {
        if (token == null) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid Token");
        }
        return Jwts.parserBuilder()
                .setSigningKey(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UUID userUuid) {
        Claims claims = parseToken(token);
        if (claims == null || !userRepository.existsById(userUuid)) {
           return false;
        }
        return !claims.getExpiration().before(new Date()) && claims.getSubject().equals(userUuid.toString());
    }

}