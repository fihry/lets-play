package org.letsplay.letsplay.model;

import lombok.Data;
import org.letsplay.letsplay.types.AuthTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;
@Document(collection = "users")
@Data
public class User implements UserDetails {
    @Id private UUID uuid = UUID.randomUUID();
    private String username;
    private String email;
    private String password;
    private AuthTypes role = AuthTypes.USER ;
    private LocalDateTime createdAt= LocalDateTime.now();
    private LocalDateTime updatedAt=LocalDateTime.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
