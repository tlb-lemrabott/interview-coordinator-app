package com.candidate.service.code.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailabilitySlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime availableTime;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;


}