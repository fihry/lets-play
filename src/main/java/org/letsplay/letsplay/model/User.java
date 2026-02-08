package org.letsplay.letsplay.model;

import lombok.Data;
import org.letsplay.letsplay.types.AuthTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collation = "users")
@Data
public class User {
    @Id private UUID uuid;
    private String username;
    private String email;
    private String password;
    private AuthTypes role = AuthTypes.USER ;
    private LocalDateTime createdAt= LocalDateTime.now();
    private LocalDateTime updatedAt=LocalDateTime.now();
}
