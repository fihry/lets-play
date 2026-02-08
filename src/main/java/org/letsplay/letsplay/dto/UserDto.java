package org.letsplay.letsplay.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.letsplay.letsplay.types.AuthTypes;

import javax.management.ConstructorParameters;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDto {
    private UUID uuid;
    private String username;
    private String email;
    private AuthTypes role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
