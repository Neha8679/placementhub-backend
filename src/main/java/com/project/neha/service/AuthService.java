package com.project.neha.service;

import com.project.neha.dto.LoginRequest;
import com.project.neha.dto.LoginResponse;
import com.project.neha.entity.User;
import com.project.neha.repository.UserRepository;
import com.project.neha.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        boolean isPasswordCorrect =
                passwordEncoder.matches(
                        request.password(),
                        user.getPasswordHash());

        if (!isPasswordCorrect) {
            throw new RuntimeException("Invalid password");
        }

        String token =
                jwtService.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}