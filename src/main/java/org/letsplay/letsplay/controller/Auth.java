package org.letsplay.letsplay.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letsplay.letsplay.dto.RegisterDto;
import org.letsplay.letsplay.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.letsplay.letsplay.dto.LoginDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class Auth {
    final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(userService.register(registerDto));
    }
}
