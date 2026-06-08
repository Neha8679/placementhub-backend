package com.project.neha.dto;

import com.project.neha.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



public record LoginResponse(
        String token,
        String role
) {
}