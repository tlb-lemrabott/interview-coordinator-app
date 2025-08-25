package com.candidate.service.code.service.impl;

import com.candidate.service.code.dto.CandidateDTO;
import com.candidate.service.code.model.Candidate;
import com.candidate.service.code.messaging.MessagePublisher;
import com.candidate.service.code.dto.CandidateMessage;
import com.candidate.service.code.repository.CandidateRepository;
import com.candidate.service.code.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final MessagePublisher messagePublisher;

    public Candidate registerCandidate(CandidateDTO dto) {
        Candidate candidate = Candidate.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .title(dto.getTitle())
                .resumeLink(dto.getResumeLink())
                .selectedTime(dto.getSelectedTime())
                .build();

        Candidate savedCandidate = candidateRepository.save(candidate);

        CandidateMessage message = CandidateMessage.builder()
                .candidateId(savedCandidate.getId())
                .candidateName(savedCandidate.getName())
                .resumeLink(savedCandidate.getResumeLink())
                .preferredTime(savedCandidate.getSelectedTime())
                .build();

        messagePublisher.sendInterviewRequest(message);

        return savedCandidate;
    }

}
