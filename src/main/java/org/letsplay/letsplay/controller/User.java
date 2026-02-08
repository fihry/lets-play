package org.letsplay.letsplay.controller;

import lombok.RequiredArgsConstructor;
import org.letsplay.letsplay.dto.UserDto;
import org.letsplay.letsplay.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/users")
public class User {
    private final UserService userService;
    @GetMapping("/{uuid}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.getUserByUuid(uuid));
    }
    @PostMapping("/{uuid}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID uuid, @RequestBody UserDto userDto) {
        userDto.setUuid(uuid);
        return ResponseEntity.ok(userService.updateUser(userDto));
    }




}
