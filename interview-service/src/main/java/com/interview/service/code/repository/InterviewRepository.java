package com.interview.service.code.repository;

import com.interview.service.code.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    Optional<Interview> findByCandidateId(Long candidateId);
}
