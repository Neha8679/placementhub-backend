package com.project.neha.controller;

import com.project.neha.dto.PersonalApplicationRequest;
import com.project.neha.dto.PersonalApplicationResponse;
import com.project.neha.service.PersonalApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personal-applications")
@RequiredArgsConstructor
public class PersonalApplicationController {

    private final PersonalApplicationService service;

    @PostMapping
    public PersonalApplicationResponse create(
            @RequestBody PersonalApplicationRequest request,
            Authentication authentication
    ) {
        return service.create(request, authentication.getName());
    }

    @GetMapping("/my")
    public List<PersonalApplicationResponse> getMyApplications(
            Authentication authentication
    ) {
        return service.getMyApplications(authentication.getName());
    }

    @PutMapping("/{id}")
    public PersonalApplicationResponse update(
            @PathVariable UUID id,
            @RequestBody PersonalApplicationRequest request,
            Authentication authentication
    ) {
        return service.update(id, request, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        service.delete(id, authentication.getName());
    }
}