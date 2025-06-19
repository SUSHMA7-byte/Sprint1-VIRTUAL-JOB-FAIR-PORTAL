package com.sprint1.service.job;

import com.sprint1.model.Job;

import java.time.LocalDateTime;

public abstract class JobNature {
    protected Job job;

    public JobNature(Job job) {
        this.job = job;
    }

    public JobNature(int jobId, String jobTitle, String jobDescription, double salary, int totalOpenings, LocalDateTime applicationStartDate, LocalDateTime applicationEndDate, String jobLocation, String jobType) {
    }

    public Job getJob() {
        return job;
    }

    public abstract String getJobNature();
}
