package com.interview.service.code.messaging;

import com.interview.service.code.config.RabbitMQConfig;
import com.interview.service.code.dto.InterviewResultDTO;
import com.interview.service.code.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InterviewMessageListener {

    private final InterviewService interviewService;

    @RabbitListener(queues = RabbitMQConfig.INTERVIEW_RESPONSE_QUEUE)
    public void receiveInterviewResult(InterviewResultDTO dto) {
        interviewService.saveInterview(dto);
        System.out.println("Saved interview result for candidate " + dto.getCandidateId());
    }


}