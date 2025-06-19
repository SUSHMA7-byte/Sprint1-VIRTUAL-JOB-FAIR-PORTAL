package com.sprint1.testing;

import com.sprint1.dao.ApplicationDAO;
import com.sprint1.dao.JobDAO;
import com.sprint1.model.Job;

public class ApplicationTesting implements Runnable {
    private final int candidateId;
    private final int jobId;

    public ApplicationTesting(int candidateId, int jobId) {
        this.candidateId = candidateId;
        this.jobId = jobId;
    }

    @Override
    public void run() {
        try {
            JobDAO jobDAO = new JobDAO();
            ApplicationDAO applicationDAO = new ApplicationDAO();
            Job job = jobDAO.getJobById(jobId);
            try {
                applicationDAO.applyForJob(candidateId, jobId, "Pending");
                System.out.println("Application submitted for job: " + job.getJobTitle());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Application Created for Candidate ID: " + candidateId + " for Job ID: " + jobId);
        } catch (Exception e) {
            System.err.println("Error creating application for Candidate ID: " + candidateId);
            e.printStackTrace();
        }
    }
}
