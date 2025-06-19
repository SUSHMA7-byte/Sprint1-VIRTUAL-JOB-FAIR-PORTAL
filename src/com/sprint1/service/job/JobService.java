package com.sprint1.service.job;

import com.sprint1.model.Candidate;
import com.sprint1.model.Job;

public interface JobService {
    void postJob(Job job);
    void displayJobDetails(Job job);

    void applyForJob(Candidate candidate, String jobId);

}
