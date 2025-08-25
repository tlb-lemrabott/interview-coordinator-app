package com.candidate.service.code.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateMessage{
    private Long candidateId;
    private String candidateName;
    private String resumeLink;
    private LocalDateTime preferredTime;
}