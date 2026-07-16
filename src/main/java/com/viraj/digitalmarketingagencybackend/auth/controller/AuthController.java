package com.viraj.digitalmarketingagencybackend.auth.controller;

import com.viraj.digitalmarketingagencybackend.auth.dto.AuthenticationResponse;
import com.viraj.digitalmarketingagencybackend.auth.dto.LoginRequest;
import com.viraj.digitalmarketingagencybackend.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "1. Authentication")
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthenticationResponse login(
            @RequestBody LoginRequest request) {
        return authService.login(request);
    }

}



