package com.interview.service.code.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewResultDTO {
    private Long candidateId;
    private Long recruiterId;
    private String status;
    private LocalDateTime scheduledTime;
    private String message;
}
