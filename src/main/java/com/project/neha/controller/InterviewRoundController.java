package com.project.neha.controller;

import com.project.neha.dto.InterviewRoundRequest;
import com.project.neha.dto.InterviewRoundResponse;
import com.project.neha.service.InterviewRoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/interview-rounds")
@RequiredArgsConstructor
public class InterviewRoundController {

    private final InterviewRoundService roundService;

    @PostMapping
    public InterviewRoundResponse createRound(@RequestBody InterviewRoundRequest request) {
        return roundService.createRound(request);
    }

    @GetMapping
    public List<InterviewRoundResponse> getAllRounds() {
        return roundService.getAllRounds();
    }

    @GetMapping("/{id}")
    public InterviewRoundResponse getRoundById(@PathVariable UUID id) {
        return roundService.getRoundById(id);
    }

    @GetMapping("/application/{applicationId}")
    public List<InterviewRoundResponse> getRoundsByApplication(@PathVariable UUID applicationId) {
        return roundService.getRoundsByApplication(applicationId);
    }

    @PutMapping("/{id}")
    public InterviewRoundResponse updateRound(
            @PathVariable UUID id,
            @RequestBody InterviewRoundRequest request
    ) {
        return roundService.updateRound(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteRound(@PathVariable UUID id) {
        roundService.deleteRound(id);
        return "Interview round deleted successfully";
    }
}