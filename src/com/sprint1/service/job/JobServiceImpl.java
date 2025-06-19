package com.sprint1.service.job;

import com.sprint1.model.Candidate;
import com.sprint1.model.Job;

public class JobServiceImpl implements JobService {
    @Override
    public void postJob(Job job) {
        System.out.println("Job Posted: " + job.getJobTitle() + " (" + job.getJobType() + ")");
    }

    @Override
    public void displayJobDetails(Job job) {
        System.out.println("Job ID: " + job.getJobId());
        System.out.println("Title: " + job.getJobTitle());
        System.out.println("Description: " + job.getJobDescription());
        System.out.println("Type: " + job.getJobType());
    }

    @Override
    public void applyForJob(Candidate candidate, String jobId) {
        System.out.println("Candidate " + candidate.getFullName() + " applied for job ID " + jobId);
    }
}
