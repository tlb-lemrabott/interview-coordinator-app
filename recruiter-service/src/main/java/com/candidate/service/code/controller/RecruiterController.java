package com.candidate.service.code.controller;

import com.candidate.service.code.dto.AvailabilitySlotDTO;
import com.candidate.service.code.dto.RecruiterDTO;
import com.candidate.service.code.entity.AvailabilitySlot;
import com.candidate.service.code.entity.Recruiter;
import com.candidate.service.code.service.RecruiterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiters")
@RequiredArgsConstructor
public class RecruiterController {
    private final RecruiterService recruiterService;

    @PostMapping
    public ResponseEntity<Recruiter> registerRecruiter(@Valid @RequestBody RecruiterDTO dto) {
        return ResponseEntity.ok(recruiterService.registerRecruiter(dto));
    }

    @PostMapping("/{recruiterId}/availability")
    public ResponseEntity<AvailabilitySlot> addAvailabilitySlot(
            @PathVariable Long recruiterId,
            @Valid @RequestBody AvailabilitySlotDTO slotDTO) {
        AvailabilitySlot savedSlot = recruiterService.addAvailabilitySlot(recruiterId, slotDTO);
        return new ResponseEntity<>(savedSlot, HttpStatus.CREATED);
    }

    @GetMapping("/{recruiterId}/availability")
    public ResponseEntity<List<AvailabilitySlot>> getAvailabilitySlots(@PathVariable Long recruiterId) {
        List<AvailabilitySlot> slots = recruiterService.getAvailabilitySlots(recruiterId);
        return ResponseEntity.ok(slots);
    }

    @PutMapping("/availability/{slotId}")
    public ResponseEntity<AvailabilitySlot> updateAvailabilitySlot(
            @PathVariable Long slotId,
            @Valid @RequestBody AvailabilitySlotDTO slotDTO) {
        AvailabilitySlot updatedSlot = recruiterService.updateAvailabilitySlot(slotId, slotDTO);
        return ResponseEntity.ok(updatedSlot);
    }

    @DeleteMapping("/availability/{slotId}")
    public ResponseEntity<Void> deleteAvailabilitySlot(@PathVariable Long slotId) {
        recruiterService.deleteAvailabilitySlot(slotId);
        return ResponseEntity.noContent().build();
    }

}