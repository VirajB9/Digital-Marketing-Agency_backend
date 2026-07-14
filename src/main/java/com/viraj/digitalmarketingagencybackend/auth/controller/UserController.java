package com.viraj.digitalmarketingagencybackend.auth.controller;

import com.viraj.digitalmarketingagencybackend.auth.dto.CreateUserRequest;
import com.viraj.digitalmarketingagencybackend.auth.dto.CreateUserResponse;
import com.viraj.digitalmarketingagencybackend.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('user:create')")
    @PostMapping
    @Operation(summary = "Create User")
    public CreateUserResponse createUser(
            @Valid @RequestBody CreateUserRequest request) {

        return userService.createUser(request);
    }
}