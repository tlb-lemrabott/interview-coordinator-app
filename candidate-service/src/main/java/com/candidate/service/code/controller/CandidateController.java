package com.candidate.service.code.controller;

import com.candidate.service.code.dto.CandidateDTO;
import com.candidate.service.code.model.Candidate;
import com.candidate.service.code.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<Candidate> registerCandidate(@RequestBody CandidateDTO candidateDTO) {
        Candidate savedCandidate = candidateService.registerCandidate(candidateDTO);
        return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
    }


}
