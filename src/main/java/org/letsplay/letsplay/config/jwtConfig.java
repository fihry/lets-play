package org.letsplay.letsplay.config;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class jwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;
}
