package com.interview.service.code.service;

import com.interview.service.code.dto.InterviewResultDTO;
import com.interview.service.code.entity.Interview;
import com.interview.service.code.repository.InterviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewRepository interviewRepository;

    public void saveInterview(InterviewResultDTO dto) {
        Interview interview = Interview.builder()
                .candidateId(dto.getCandidateId())
                .recruiterId(dto.getRecruiterId())
                .status(dto.getStatus())
                .scheduledTime(dto.getScheduledTime())
                .message(dto.getMessage())
                .build();

        interviewRepository.save(interview);
    }

    public Optional<Interview> getInterviewByCandidateId(Long candidateId) {
        return interviewRepository.findByCandidateId(candidateId);
    }


}
