package org.letsplay.letsplay.config;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;
}
