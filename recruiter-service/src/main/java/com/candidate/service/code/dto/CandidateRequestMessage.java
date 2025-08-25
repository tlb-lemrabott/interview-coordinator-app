package com.candidate.service.code.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateRequestMessage{
    private Long candidateId;
    private String candidateName;
    private String resumeLink;
    private LocalDateTime preferredTime;


}
