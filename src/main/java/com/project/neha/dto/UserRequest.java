package com.project.neha.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(

        @NotBlank
        String name,

        @Email
        String email,

        @NotBlank
        String password,

        String college,

        String branch,

        @NotNull
        Integer graduationYear,
        String resumeUrl
) {}
