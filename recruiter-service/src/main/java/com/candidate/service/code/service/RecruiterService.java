package com.candidate.service.code.service;

import com.candidate.service.code.dto.AvailabilitySlotDTO;
import com.candidate.service.code.dto.CandidateRequestMessage;
import com.candidate.service.code.dto.InterviewResponseMessage;
import com.candidate.service.code.dto.RecruiterDTO;
import com.candidate.service.code.entity.AvailabilitySlot;
import com.candidate.service.code.entity.Recruiter;
import com.candidate.service.code.entity.SlotStatus;
import com.candidate.service.code.repository.AvailabilitySlotRepository;
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
    private final AvailabilitySlotRepository availabilitySlotRepository;
    private final RabbitTemplate rabbitTemplate;

    public Recruiter registerRecruiter(RecruiterDTO dto) {
        Recruiter recruiter = Recruiter.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();

        return recruiterRepository.save(recruiter);
    }

    public void processCandidateRequest(CandidateRequestMessage request) {
        List<AvailabilitySlot> availableSlots = availabilitySlotRepository
                .findByStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus(
                        request.getPreferredTime(), 
                        request.getPreferredTime(), 
                        SlotStatus.AVAILABLE);

        if (!availableSlots.isEmpty()) {
            AvailabilitySlot slot = availableSlots.get(0);
            InterviewResponseMessage response = InterviewResponseMessage.builder()
                    .candidateId(request.getCandidateId())
                    .recruiterId(slot.getRecruiter().getId())
                    .status("ACCEPTED")
                    .scheduledTime(request.getPreferredTime())
                    .message("Interview accepted by recruiter " + slot.getRecruiter().getName())
                    .build();

            rabbitTemplate.convertAndSend(INTERVIEW_RESPONSE_QUEUE, response);
            return;
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

    public AvailabilitySlot addAvailabilitySlot(Long recruiterId, AvailabilitySlotDTO slotDTO) {
        Recruiter recruiter = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        AvailabilitySlot slot = AvailabilitySlot.builder()
                .startTime(slotDTO.getStartTime())
                .endTime(slotDTO.getEndTime())
                .notes(slotDTO.getNotes())
                .status(slotDTO.getStatus())
                .recruiter(recruiter)
                .build();

        return availabilitySlotRepository.save(slot);
    }

    public List<AvailabilitySlot> getAvailabilitySlots(Long recruiterId) {
        return availabilitySlotRepository.findByRecruiterId(recruiterId);
    }

    public AvailabilitySlot updateAvailabilitySlot(Long slotId, AvailabilitySlotDTO slotDTO) {
        AvailabilitySlot slot = availabilitySlotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Availability slot not found"));

        slot.setStartTime(slotDTO.getStartTime());
        slot.setEndTime(slotDTO.getEndTime());
        slot.setNotes(slotDTO.getNotes());
        slot.setStatus(slotDTO.getStatus());

        return availabilitySlotRepository.save(slot);
    }

    public void deleteAvailabilitySlot(Long slotId) {
        availabilitySlotRepository.deleteById(slotId);
    }
}
