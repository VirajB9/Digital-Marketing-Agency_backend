package com.viraj.digitalmarketingagencybackend.auth.service;

import com.viraj.digitalmarketingagencybackend.auth.dto.CreateUserRequest;
import com.viraj.digitalmarketingagencybackend.auth.dto.UserResponse;
import com.viraj.digitalmarketingagencybackend.auth.entity.Role;
import com.viraj.digitalmarketingagencybackend.auth.entity.User;
import com.viraj.digitalmarketingagencybackend.auth.enmus.UserStatus;
import com.viraj.digitalmarketingagencybackend.auth.repository.RoleRepository;
import com.viraj.digitalmarketingagencybackend.auth.repository.UserRepository;
import com.viraj.digitalmarketingagencybackend.common.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository
                .findById(request.getRoleId())
                .orElseThrow(() ->
                        new RuntimeException("Role not found"));

        String generatedPassword =
                PasswordGenerator.generatePassword();

        String encodedPassword =
                passwordEncoder.encode(generatedPassword);

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(encodedPassword)
                .roleId(role.getId())
                .status(UserStatus.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .phoneNumber(savedUser.getPhoneNumber())
                .roleName(role.getName())
                .status(savedUser.getStatus())
                .build();
    }
}