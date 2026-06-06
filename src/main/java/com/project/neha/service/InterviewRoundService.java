package com.project.neha.service;

import com.project.neha.dto.InterviewRoundRequest;
import com.project.neha.dto.InterviewRoundResponse;
import com.project.neha.entity.Application;
import com.project.neha.entity.InterviewRound;
import com.project.neha.repository.ApplicationRepository;
import com.project.neha.repository.InterviewRoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewRoundService {

    private final InterviewRoundRepository roundRepository;
    private final ApplicationRepository applicationRepository;

    private InterviewRoundResponse mapToResponse(InterviewRound round) {
        return new InterviewRoundResponse(
                round.getId(),
                round.getApplication().getId(),
                round.getRoundNumber(),
                round.getRoundType(),
                round.getOutcome(),
                round.getNotes(),
                round.getRoundDate()
        );
    }

    public InterviewRoundResponse createRound(InterviewRoundRequest request) {

        Application application = applicationRepository.findById(request.applicationId())
                .orElseThrow(() -> new RuntimeException("Application not found"));

        InterviewRound round = new InterviewRound();
        round.setApplication(application);
        round.setRoundNumber(request.roundNumber());
        round.setRoundType(request.roundType());
        round.setOutcome(request.outcome());
        round.setNotes(request.notes());
        round.setRoundDate(request.roundDate());

        InterviewRound savedRound = roundRepository.save(round);

        return mapToResponse(savedRound);
    }

    public List<InterviewRoundResponse> getRoundsByApplication(UUID applicationId) {
        return roundRepository.findByApplication_Id(applicationId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<InterviewRoundResponse> getAllRounds() {
        return roundRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public InterviewRoundResponse getRoundById(UUID id) {
        InterviewRound round = roundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview round not found"));

        return mapToResponse(round);
    }

    public InterviewRoundResponse updateRound(UUID id, InterviewRoundRequest request) {

        InterviewRound round = roundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview round not found"));

        Application application = applicationRepository.findById(request.applicationId())
                .orElseThrow(() -> new RuntimeException("Application not found"));

        round.setApplication(application);
        round.setRoundNumber(request.roundNumber());
        round.setRoundType(request.roundType());
        round.setOutcome(request.outcome());
        round.setNotes(request.notes());
        round.setRoundDate(request.roundDate());

        InterviewRound updatedRound = roundRepository.save(round);

        return mapToResponse(updatedRound);
    }

    public void deleteRound(UUID id) {

        InterviewRound round = roundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview round not found"));

        roundRepository.delete(round);
    }
}