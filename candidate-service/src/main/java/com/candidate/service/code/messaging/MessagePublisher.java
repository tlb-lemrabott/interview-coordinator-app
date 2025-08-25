package com.candidate.service.code.messaging;


import com.candidate.service.code.config.RabbitMQConfig;
import com.candidate.service.code.dto.CandidateMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class MessagePublisher{
    private final RabbitTemplate rabbitTemplate;

    public void sendInterviewRequest(CandidateMessage message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.INTERVIEW_REQUEST_QUEUE, message);
    }
}
