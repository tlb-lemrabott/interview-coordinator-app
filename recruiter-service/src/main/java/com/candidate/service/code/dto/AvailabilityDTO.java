package com.candidate.service.code.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailabilityDTO{
    private LocalDateTime availableTime;


}
