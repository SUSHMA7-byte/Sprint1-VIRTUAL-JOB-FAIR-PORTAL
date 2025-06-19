package com.sprint1.service.candidate;

import com.sprint1.dao.*;
import com.sprint1.model.*;
import com.sprint1.service.notification.*;

import java.time.LocalDateTime;
import java.util.*;

public class CandidateServiceImpl {

    private CandidateDAO candidateDAO = new CandidateDAO();
    private JobDAO jobDAO = new JobDAO();
    private ApplicationDAO applicationDAO = new ApplicationDAO();
    private InterviewDAO interviewDAO = new InterviewDAO();

    public void candidateDashboard(Scanner sc, Candidate candidate) {
        System.out.println("Welcome, " + candidate.getFullName());

        int choice;
        do {
            System.out.println("\n===== CANDIDATE DASHBOARD =====");
            System.out.println("1. View All Active Jobs");
            System.out.println("2. Apply for Job");
            System.out.println("3. View My Applications");
            System.out.println("4. View Scheduled Interviews");
            System.out.println("5. Logout");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewAllJobs();
                case 2 -> applyForJob(sc, candidate);
                case 3 -> viewMyApplications(candidate);
                case 4 -> viewMyInterviews(candidate);
                case 5 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private void viewAllJobs() {
        List<Job> jobs = jobDAO.getAllActiveJobs();
        if (jobs.isEmpty()) {
            System.out.println("No active jobs available.");
        } else {
            for (Job job : jobs) {
                System.out.println(job);
                System.out.println("------------------------");
            }
        }
    }

    private void applyForJob(Scanner sc, Candidate candidate) {
        System.out.print("Enter Job ID to apply: ");
        int jobId = sc.nextInt();
        sc.nextLine();

        Job job = jobDAO.getJobById(jobId);
        if (job == null) {
            System.out.println("Invalid Job ID.");
            return;
        }

        boolean alreadyApplied = applicationDAO.checkApplicationExists(candidate.getCandidateId(), jobId);
        if (alreadyApplied) {
            System.out.println("You have already applied for this job.");
            return;
        }

        try {
            applicationDAO.applyForJob(candidate.getCandidateId(), jobId, "Pending");
            System.out.println("Application submitted for job: " + job.getJobTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewMyApplications(Candidate candidate) {
        List<Job> appliedJobs = applicationDAO.getJobsAppliedByCandidate(candidate.getCandidateId());
        if (appliedJobs.isEmpty()) {
            System.out.println("You haven't applied for any jobs yet.");
        } else {
            System.out.println("Jobs Applied:");
            for (Job job : appliedJobs) {
                System.out.println(job);
                System.out.println("------------------------");
            }
        }
    }

    private void viewMyInterviews(Candidate candidate) {
        List<Interview> interviews = interviewDAO.getInterviewsByCandidateId(candidate.getCandidateId());
        if (interviews.isEmpty()) {
            System.out.println("No interviews scheduled yet.");
            return;
        }

        for (Interview interview : interviews) {
            //System.out.println("Interview Type: " + interview.getClass().getSimpleName().replace("Service", ""));
            System.out.println("Interview Type: " + interview.getInterviewType());
            System.out.println("Scheduled on: " + interview.getInterviewDatetime());

            Notification notification;
            String contactInfo;

            if (candidate.getPhoneNumber() != null && !candidate.getPhoneNumber().isEmpty()) {
                notification = new SMSNotification();
                contactInfo = candidate.getPhoneNumber();
            } else {
                notification = new EmailNotification();
                contactInfo = candidate.getEmail();
            }

            notification.send(contactInfo, "Your interview is scheduled on: " + interview.getInterviewDatetime());
            System.out.println("------------------------");
        }
    }
}
