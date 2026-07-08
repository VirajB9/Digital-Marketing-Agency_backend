package com.viraj.digitalmarketingagencybackend.auth.bootstrap;

import com.viraj.digitalmarketingagencybackend.auth.enmus.UserStatus;
import com.viraj.digitalmarketingagencybackend.auth.entity.Role;
import com.viraj.digitalmarketingagencybackend.auth.entity.User;
import com.viraj.digitalmarketingagencybackend.auth.repository.RoleRepository;
import com.viraj.digitalmarketingagencybackend.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OwnerBootstrap implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Optional<Role> ownerRole = roleRepository.findByName("Owner");
        if (ownerRole.isPresent()) {
            return;
        }

        Role role = Role.builder()
                .name("Owner")
                .build();

        Role savedRole = roleRepository.save(role);

        String encodedPassword = passwordEncoder.encode("Owner@123");

        User owner = User.builder()
                .firstName("Owner")
                .lastName("Admin")
                .email("owner@agency.com")
                .phoneNumber("9999999999")
                .password(encodedPassword)
                .roleId(savedRole.getId())
                .status(UserStatus.ACTIVE)
                .build();

        userRepository.save(owner);
    }
}
