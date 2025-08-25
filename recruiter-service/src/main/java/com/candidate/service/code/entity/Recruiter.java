package com.candidate.service.code.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AvailabilitySlot> availabilitySlots;


}

