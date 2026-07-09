package com.viraj.digitalmarketingagencybackend.auth.controller;

import com.viraj.digitalmarketingagencybackend.auth.dto.CreateUserRequest;
import com.viraj.digitalmarketingagencybackend.auth.dto.UserResponse;
import com.viraj.digitalmarketingagencybackend.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create User")
    public UserResponse createUser(
            @RequestBody CreateUserRequest request) {

        return userService.createUser(request);
    }
}