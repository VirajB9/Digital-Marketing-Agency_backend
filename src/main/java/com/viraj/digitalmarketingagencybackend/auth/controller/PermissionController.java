package com.viraj.digitalmarketingagencybackend.auth.controller;

import com.viraj.digitalmarketingagencybackend.auth.dto.PermissionResponse;
import com.viraj.digitalmarketingagencybackend.auth.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "4. Permission Management")
@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class PermissionController {

    private final PermissionService permissionService;

    @PreAuthorize("hasAuthority('permission:read')")
    @GetMapping
    @Operation(summary = "Get All Permissions")
    public List<PermissionResponse> getAllPermissions() {

        return permissionService.getAllPermissions();
    }
}