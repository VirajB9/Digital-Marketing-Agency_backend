package com.viraj.digitalmarketingagencybackend.auth.controller;

import com.viraj.digitalmarketingagencybackend.auth.dto.CreateUserRequest;
import com.viraj.digitalmarketingagencybackend.auth.dto.UserResponse;
import com.viraj.digitalmarketingagencybackend.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(
            @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }
}
