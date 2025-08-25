package com.candidate.service.code.repository;

import com.candidate.service.code.entity.AvailabilitySlot;
import com.candidate.service.code.entity.SlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilitySlotRepository extends JpaRepository<AvailabilitySlot, Long> {
    Optional<AvailabilitySlot> findByAvailableTime(LocalDateTime availableTime);
    
    List<AvailabilitySlot> findByRecruiterId(Long recruiterId);
    
    List<AvailabilitySlot> findByStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus(
            LocalDateTime startTime, LocalDateTime endTime, SlotStatus status);
}
