package com.interview.service.code.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long candidateId;
    private Long recruiterId;
    private String status; // ACCEPTED / DECLINED
    private LocalDateTime scheduledTime;
    private String message;


}

