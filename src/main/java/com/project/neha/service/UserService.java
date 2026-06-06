package com.project.neha.service;

import com.project.neha.dto.LoginRequest;
import com.project.neha.dto.UserResponse;
import com.project.neha.entity.User;
import com.project.neha.enums.Role;
import com.project.neha.repository.UserRepository;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.neha.dto.UserResponse;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.project.neha.dto.UserRequest;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCollege(),
                user.getBranch(),
                user.getGraduationYear(),
                user.getResumeUrl(),
                user.getRole(),
                user.getCreatedAt()
        );
    }


    public UserResponse save(UserRequest request) {

        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPasswordHash(
                passwordEncoder.encode(request.password())
        );
        user.setCollege(request.college());
        user.setBranch(request.branch());
        user.setGraduationYear(request.graduationYear());
        user.setResumeUrl(request.resumeUrl());
        user.setRole(Role.STUDENT);


        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }
    public User updateUser(UUID id, User updatedUser) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setCollege(updatedUser.getCollege());
        user.setBranch(updatedUser.getBranch());
        user.setGraduationYear(updatedUser.getGraduationYear());

        return userRepository.save(user);
    }
    public void deleteUser(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        boolean matches =
                passwordEncoder.matches(
                        request.password(),
                        user.getPasswordHash());

        if (!matches) {
            throw new RuntimeException("Invalid Password");
        }

        return "Login Successful";
    }




}
