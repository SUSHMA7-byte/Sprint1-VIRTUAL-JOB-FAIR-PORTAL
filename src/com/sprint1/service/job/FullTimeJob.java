package com.sprint1.service.job;

import com.sprint1.model.Job;

import java.time.LocalDateTime;

public class FullTimeJob extends JobNature {

    public FullTimeJob(int job_id, String job_title, String job_description, double salary, int total_openings,
                       LocalDateTime application_start_date, LocalDateTime application_end_date,
                       String job_location, String job_type) {
        super(job_id, job_title, job_description, salary, total_openings,
                application_start_date, application_end_date, job_location, job_type);
    }

    @Override
    public String getJobNature() {
        return "Full-Time Employment";
    }
}
