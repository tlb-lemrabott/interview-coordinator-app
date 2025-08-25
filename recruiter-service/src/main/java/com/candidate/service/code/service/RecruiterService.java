package com.candidate.service.code.service;

import com.candidate.service.code.dto.CandidateRequestMessage;
import com.candidate.service.code.dto.InterviewResponseMessage;
import com.candidate.service.code.dto.RecruiterDTO;
import com.candidate.service.code.entity.AvailabilitySlot;
import com.candidate.service.code.entity.Recruiter;
import com.candidate.service.code.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.candidate.service.code.config.RabbitMQConfig.INTERVIEW_RESPONSE_QUEUE;

@Service
@RequiredArgsConstructor
public class RecruiterService {
    private final RecruiterRepository recruiterRepository;
    private final RabbitTemplate rabbitTemplate;

    public Recruiter registerRecruiter(RecruiterDTO dto) {
        Recruiter recruiter = Recruiter.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();

        List<AvailabilitySlot> slots = dto.getAvailabilitySlots().stream()
                .map(slotDTO -> AvailabilitySlot.builder()
                        .availableTime(slotDTO.getAvailableTime())
                        .recruiter(recruiter)
                        .build())
                .collect(Collectors.toList());

        recruiter.setAvailabilitySlots(slots);
        return recruiterRepository.save(recruiter);
    }

    public void processCandidateRequest(CandidateRequestMessage request) {
        List<Recruiter> recruiters = recruiterRepository.findAll();

        for (Recruiter recruiter : recruiters) {
            boolean matched = recruiter.getAvailabilitySlots().stream()
                    .anyMatch(slot -> slot.getAvailableTime().equals(request.getPreferredTime()));

            if (matched) {
                InterviewResponseMessage response = InterviewResponseMessage.builder()
                        .candidateId(request.getCandidateId())
                        .recruiterId(recruiter.getId())
                        .status("ACCEPTED")
                        .scheduledTime(request.getPreferredTime())
                        .message("Interview accepted by recruiter " + recruiter.getName())
                        .build();

                rabbitTemplate.convertAndSend(INTERVIEW_RESPONSE_QUEUE, response);
                return;
            }
        }

        InterviewResponseMessage declined = InterviewResponseMessage.builder()
                .candidateId(request.getCandidateId())
                .recruiterId(null)
                .status("DECLINED")
                .scheduledTime(request.getPreferredTime())
                .message("No recruiter is available at the requested time.")
                .build();

        rabbitTemplate.convertAndSend(INTERVIEW_RESPONSE_QUEUE, declined);
    }

}
