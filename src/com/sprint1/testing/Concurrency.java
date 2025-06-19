package com.sprint1.testing;

import com.sprint1.model.Candidate;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrency {
    private final int jobId;
    private final int threadCount;
    private List<Candidate> candidates;

    public Concurrency(int jobId, int threadCount) {
        this.jobId = jobId;
        this.threadCount = threadCount;
    }

    public void runCandidateCreationOnly() {
        candidates = CandidateTesting.generateTestingCandidates(threadCount);

        ExecutorService candidateExecutor = Executors.newFixedThreadPool(threadCount);
        for (Candidate candidate : candidates) {
            candidateExecutor.execute(new CandidateTesting(candidate));
        }
        candidateExecutor.shutdown();
        while (!candidateExecutor.isTerminated()) {
        }

        System.out.println("All candidates created.");
    }

    public void runApplicationCreationOnly() {
        if (candidates == null || candidates.isEmpty()) {
            System.out.println("No candidates available. Run candidate creation first.");
            return;
        }

        ExecutorService appExecutor = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount && i < candidates.size(); i++) {
            int candidateId = candidates.get(i).getCandidateId();
            appExecutor.execute(new ApplicationTesting(candidateId, jobId));
        }
        appExecutor.shutdown();
        while (!appExecutor.isTerminated()) {
        }

        System.out.println("All applications submitted.");
    }
}
