package com.candidate.service.code.controller;

import com.candidate.service.code.dto.RecruiterDTO;
import com.candidate.service.code.entity.Recruiter;
import com.candidate.service.code.service.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recruiters")
@RequiredArgsConstructor
public class RecruiterController {
    private final RecruiterService recruiterService;

    @PostMapping
    public ResponseEntity<Recruiter> registerRecruiter(@RequestBody RecruiterDTO dto) {
        return ResponseEntity.ok(recruiterService.registerRecruiter(dto));
    }

}