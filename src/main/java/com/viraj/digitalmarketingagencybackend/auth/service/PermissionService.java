package com.viraj.digitalmarketingagencybackend.auth.service;

import com.viraj.digitalmarketingagencybackend.auth.dto.PermissionResponse;
import com.viraj.digitalmarketingagencybackend.auth.entity.Permission;
import com.viraj.digitalmarketingagencybackend.auth.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public List<PermissionResponse> getAllPermissions() {

        List<Permission> permissions = permissionRepository.findAll();

        return permissions.stream()
                .map(this::mapPermission)
                .toList();
    }

    private PermissionResponse mapPermission(
            Permission permission) {

        return PermissionResponse.builder()
                .id(permission.getId())
                .name(permission.getPermissionType().name())
                .description(permission.getDescription())
                .build();
    }
}