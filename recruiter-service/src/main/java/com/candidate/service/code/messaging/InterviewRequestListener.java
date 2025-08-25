package com.candidate.service.code.messaging;


import com.candidate.service.code.config.RabbitMQConfig;
import com.candidate.service.code.dto.CandidateRequestMessage;
import com.candidate.service.code.service.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InterviewRequestListener {
    private final RecruiterService recruiterService;

    @RabbitListener(queues = RabbitMQConfig.INTERVIEW_REQUEST_QUEUE)
    public void listen(CandidateRequestMessage message) {
        recruiterService.processCandidateRequest(message);
    }


}