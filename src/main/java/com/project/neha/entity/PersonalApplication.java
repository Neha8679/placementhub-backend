package com.project.neha.entity;

import com.project.neha.enums.PersonalApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String companyName;

    private String role;

    private String source;

    private LocalDate appliedDate;

    @Enumerated(EnumType.STRING)
    private PersonalApplicationStatus status;

    private LocalDate oaDate;

    private LocalDate interviewDate;

    private BigDecimal packageLpa;

    @Column(columnDefinition = "TEXT")
    private String notes;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();

        if (status == null) {
            status = PersonalApplicationStatus.APPLIED;
        }
    }
}
