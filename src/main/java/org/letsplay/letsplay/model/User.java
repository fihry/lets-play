package org.letsplay.letsplay.model;

import org.letsplay.letsplay.types.AuthTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collation = "users")
public class User {
    @Id private UUID uuid;
    private String username;
    private String email;
    private String password;
    private AuthTypes authTypes = AuthTypes.USER ;
}
