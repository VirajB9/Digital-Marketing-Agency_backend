package com.viraj.digitalmarketingagencybackend.auth.bootstrap;

import com.viraj.digitalmarketingagencybackend.auth.enmus.UserStatus;
import com.viraj.digitalmarketingagencybackend.auth.entity.Role;
import com.viraj.digitalmarketingagencybackend.auth.entity.User;
import com.viraj.digitalmarketingagencybackend.auth.repository.RoleRepository;
import com.viraj.digitalmarketingagencybackend.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(3)
public class OwnerBootstrap implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("owner@agency").isPresent()) {
            return;
        }

        Role ownerRole = roleRepository.findByName("OWNER")
                .orElseThrow(() -> new RuntimeException("OWNER role not found"));

        User owner = User.builder()
                .firstName("Owner")
                .lastName("Admin")
                .email("owner@agency.com")
                .phoneNumber("9999999999")
                .password(passwordEncoder.encode("Owner@123"))
                .roleId(ownerRole.getId())
                .status(UserStatus.ACTIVE)
                .build();

        userRepository.save(owner);
    }
}
