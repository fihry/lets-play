package org.letsplay.letsplay.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.letsplay.letsplay.dto.loginDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class Auth {
    @GetMapping("/login")
    public ResponseEntity<loginDto> login(){
        return ResponseEntity.ok(new loginDto());
    }
}
