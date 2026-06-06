package com.project.neha.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(
        name = "placement_stats",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"company_id", "year"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlacementStat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private Integer year;

    private Integer studentsSelected = 0;

    private BigDecimal avgPackage;
}
