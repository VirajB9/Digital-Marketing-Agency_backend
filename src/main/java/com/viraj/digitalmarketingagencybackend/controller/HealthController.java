package com.viraj.digitalmarketingagencybackend.controller;

import com.viraj.digitalmarketingagencybackend.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ApiResponse<String> health() {

        return new ApiResponse<>(
                true,
                "Backend is running successfully",
                "OK"
        );
    }

}
