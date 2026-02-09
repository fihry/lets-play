package org.letsplay.letsplay.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.letsplay.letsplay.Exceptions.ApiException;
import org.letsplay.letsplay.dto.LoginDto;
import org.letsplay.letsplay.dto.RegisterDto;
import org.letsplay.letsplay.dto.LoginResponse;
import org.letsplay.letsplay.dto.UserDto;
import org.letsplay.letsplay.model.User;
import org.letsplay.letsplay.repository.UserRepository;
import org.letsplay.letsplay.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.letsplay.letsplay.types.AuthTypes;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Log log = LogFactory.getLog(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public LoginResponse login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        var user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));
        
        var jwtToken = jwtService.generateToken(user);
        var response = new LoginResponse();
        response.setToken(jwtToken);
        return response;
    }

    public LoginResponse register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
             throw new ApiException(HttpStatus.BAD_REQUEST, "Username is already taken");
        }
        var user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        user.setRole(AuthTypes.USER);
        
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Error saving user: " + e.getMessage());
        }
        var response = new LoginResponse();
        response.setToken(jwtService.generateToken(user));
        return response;
    }

    public UserDto getUserByUuid(UUID uuid) {
        var user = userRepository.findByUuid(uuid).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));
        var response = new UserDto();
        response.setUuid(user.getUuid());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }

    public List<UserDto> getUsers() {
        var  users = userRepository.findAll();
        List<UserDto> allUsers = List.of();
        users.forEach(user -> {
            UserDto userDto = new UserDto();
            userDto.setUuid(user.getUuid());
            userDto.setEmail(user.getEmail());
            userDto.setUsername(user.getUsername());
            userDto.setRole(user.getRole());
            userDto.setCreatedAt(user.getCreatedAt());
            userDto.setUpdatedAt(user.getUpdatedAt());
        });
        return allUsers;
    }

    public UserDto updateUser(UserDto userDto) {
        var currentUser = authentication.getPrincipal();
        var updatedUser = new User();
        try{
            log.info(currentUser.toString());
            if (userDto.getUsername() != null ) {
                if (userRepository.existsByUsername(userDto.getUsername())){
                    throw  new ApiException(HttpStatus.BAD_REQUEST, "Username is already taken");
                }
                updatedUser.setUsername(userDto.getUsername());
            }
            if (userDto.getEmail() != null ) {
                if (userRepository.existsByEmail(userDto.getEmail())){
                    throw  new ApiException(HttpStatus.BAD_REQUEST, "Email is already taken");
                }
                updatedUser.setEmail(userDto.getEmail());
            }
            userRepository.save(updatedUser);
        } catch (Exception e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }
        return   userDto;
    }
}
