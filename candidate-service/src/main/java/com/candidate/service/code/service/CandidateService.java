package com.candidate.service.code.service;

import com.candidate.service.code.dto.CandidateDTO;
import com.candidate.service.code.model.Candidate;

public interface CandidateService {
    Candidate registerCandidate(CandidateDTO dto);
}
