package com.example.agency.cars.controller;

import com.example.agency.cars.dto.UserRequest;
import com.example.agency.cars.dto.UserResponse;
import com.example.agency.cars.service.UserServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User control and management")
public class UserController {

    private final UserServiceImpl service;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public UserResponse create(@RequestBody UserRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserResponse created = service.create(user);
        return created;
    }
}
