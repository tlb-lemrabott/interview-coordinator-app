package com.candidate.service.code.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewResponseMessage{
    private Long candidateId;
    private Long recruiterId;
    private String status;
    private LocalDateTime scheduledTime;
    private String message;


}
