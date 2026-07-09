package com.viraj.digitalmarketingagencybackend.auth.service;

import com.viraj.digitalmarketingagencybackend.auth.dto.AuthenticationResponse;
import com.viraj.digitalmarketingagencybackend.auth.dto.LoginRequest;
import com.viraj.digitalmarketingagencybackend.auth.dto.UserResponse;
import com.viraj.digitalmarketingagencybackend.auth.entity.Role;
import com.viraj.digitalmarketingagencybackend.auth.entity.User;
import com.viraj.digitalmarketingagencybackend.auth.repository.RoleRepository;
import com.viraj.digitalmarketingagencybackend.auth.repository.UserRepository;
import com.viraj.digitalmarketingagencybackend.auth.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final JwtUtil jwtUtil;

    public AuthenticationResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        Role role = roleRepository
                .findById(user.getRoleId())
                .orElseThrow(() ->
                        new RuntimeException("Role not found"));

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .roleName(role.getName())
                .status(user.getStatus())
                .build();

        String token = jwtUtil.generateToken(user.getEmail());

        return AuthenticationResponse.builder()
                .token(token)
                .type("Bearer")
                .user(userResponse)
                .build();
    }
}
