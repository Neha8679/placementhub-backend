package com.project.neha.entity;

import com.project.neha.enums.RoundType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "interview_rounds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterviewRound {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    private Integer roundNumber;

    @Enumerated(EnumType.STRING)
    private RoundType roundType;

    private String outcome;

    private String notes;

    private LocalDate roundDate;
}
