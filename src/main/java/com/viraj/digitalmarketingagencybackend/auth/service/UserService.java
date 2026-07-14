package com.viraj.digitalmarketingagencybackend.auth.service;

import com.viraj.digitalmarketingagencybackend.auth.dto.CreateUserRequest;
import com.viraj.digitalmarketingagencybackend.auth.dto.CreateUserResponse;
import com.viraj.digitalmarketingagencybackend.auth.dto.UserResponse;
import com.viraj.digitalmarketingagencybackend.auth.enmus.UserStatus;
import com.viraj.digitalmarketingagencybackend.auth.entity.Role;
import com.viraj.digitalmarketingagencybackend.auth.entity.User;
import com.viraj.digitalmarketingagencybackend.auth.exception.DuplicateEmailException;
import com.viraj.digitalmarketingagencybackend.auth.exception.DuplicatePhoneException;
import com.viraj.digitalmarketingagencybackend.auth.exception.RoleNotFoundException;
import com.viraj.digitalmarketingagencybackend.auth.exception.UnauthorizedRoleAssignmentException;
import com.viraj.digitalmarketingagencybackend.auth.repository.RoleRepository;
import com.viraj.digitalmarketingagencybackend.auth.repository.UserRepository;
import com.viraj.digitalmarketingagencybackend.auth.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.viraj.digitalmarketingagencybackend.common.util.PasswordGenerator.generatePassword;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public CreateUserResponse createUser(CreateUserRequest request) {

        validateDuplicateEmail(request.getEmail());

        validateDuplicatePhone(request.getPhoneNumber());

        Role role = getRoleById(request.getRoleId());

        User currentUser = getCurrentUser();

        validateRoleAssignment(currentUser, role);

        String generatedPassword = generatePassword();

        String encodedPassword =
                passwordEncoder.encode(generatedPassword);

        User user = buildUser(request, role, encodedPassword);

        User savedUser = userRepository.save(user);

        return CreateUserResponse.builder()
                .user(mapToUserResponse(savedUser, role))
                .temporaryPassword(generatedPassword)
                .build();
    }

    private void validateDuplicateEmail(String email) {

        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(email);
        }
    }

    private void validateDuplicatePhone(String phoneNumber) {

        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DuplicatePhoneException(phoneNumber);
        }
    }

    private Role getRoleById(String roleId) {

        return roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new RoleNotFoundException(roleId));
    }

    private User buildUser(
            CreateUserRequest request,
            Role role,
            String encodedPassword) {

        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(encodedPassword)
                .roleId(role.getId())
                .status(UserStatus.ACTIVE)
                .build();
    }

    private UserResponse mapToUserResponse(
            User user,
            Role role) {

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .roleName(role.getName())
                .status(user.getStatus())
                .build();
    }

    private User getCurrentUser() {

        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        return userDetails.getUser();
    }

    /**
     * Validates whether the authenticated user
     * is allowed to assign the requested role.
     */
    private void validateRoleAssignment(
            User currentUser,
            Role targetRole) {

        Role currentUserRole = getRoleById(currentUser.getRoleId());

        String currentRole = currentUserRole.getName();

        String targetRoleName = targetRole.getName();

        switch (currentRole) {
            case "OWNER":
                return;

            case "MANAGER":
                if (!targetRoleName.equals("EMPLOYEE")
                        && !targetRoleName.equals("INTERN")) {
                    throw new UnauthorizedRoleAssignmentException(
                            "Managers can only create Employees or Interns"
                    );
                }
                return;

            case "EMPLOYEE":
            case "INTERN":
                throw new UnauthorizedRoleAssignmentException(
                        "You are not allowed to create users"
                );
            default:
                throw new UnauthorizedRoleAssignmentException(
                        "Invalid role assignment"
                );
        }
    }
}