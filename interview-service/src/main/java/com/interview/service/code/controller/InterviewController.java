package com.interview.service.code.controller;

import com.interview.service.code.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewService interviewService;

    @GetMapping("/{candidateId}")
    public ResponseEntity<?> getInterviewByCandidateId(@PathVariable Long candidateId) {
        return interviewService.getInterviewByCandidateId(candidateId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
