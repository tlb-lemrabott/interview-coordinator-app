package com.candidate.service.code.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruiterDTO{
    private String name;
    private String email;
    private List<AvailabilityDTO> availabilitySlots;


}
