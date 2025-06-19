package com.sprint1.testing;

import com.sprint1.dao.CandidateDAO;
import com.sprint1.model.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidateTesting implements Runnable {
    private final Candidate candidate;

    public CandidateTesting(Candidate candidate) {
        this.candidate = candidate;
    }

    public void run() {
        try {
            CandidateDAO candidateDAO = new CandidateDAO();
            int generated_id = candidateDAO.registerTestingCandidate(candidate);
            candidate.setCandidateId(generated_id);
            System.out.println("Candidate Created in DB: " + candidate.getFullName());
        } catch (Exception e) {
            System.err.println("Error saving candidate: " + candidate.getFullName());
            e.printStackTrace();
        }
    }

    public static List<Candidate> generateTestingCandidates(int count) {
        List<Candidate> candidates = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Candidate c = new Candidate(
                    "test_candidate_" + i,
                    "test_candidate_" + i + "@mail.com",
                    "9999999999",
                    "Dummy Resume",
                    "Test College",
                    "India"
            );
            candidates.add(c);
        }
        return candidates;
    }
}
