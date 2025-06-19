package com.sprint1.service.company;

import com.sprint1.dao.CompanyDAO;
import com.sprint1.dao.JobDAO;
import com.sprint1.dao.ApplicationDAO;
import com.sprint1.dao.InterviewDAO;
import com.sprint1.exception.company.DuplicateCompanyEmailException;
import com.sprint1.model.*;
import com.sprint1.service.interview.HRInterviewService;
import com.sprint1.service.interview.TechnicalInterviewService;

import java.time.LocalDateTime;
import java.util.*;

public class CompanyServiceImpl{

    private CompanyDAO companyDAO = new CompanyDAO();
    private JobDAO jobDAO = new JobDAO();
    private ApplicationDAO applicationDAO = new ApplicationDAO();
    private InterviewDAO interviewDAO = new InterviewDAO();

    public void registerCompany(Company company) throws DuplicateCompanyEmailException {
        companyDAO.insertCompany(company);
    }

    public Company loginCompany(String email) {
        return companyDAO.getCompanyByEmail(email);
    }


    public void companyDashboard(Scanner sc,int companyId) {
        int choice;
        do {
            System.out.println("\n===== COMPANY DASHBOARD =====");
            System.out.println("1. Post a New Job");
            System.out.println("2. View All Posted Jobs");
            System.out.println("3. View Applications to My Jobs");
            System.out.println("4. Schedule Interview");
            System.out.println("5. View Scheduled Interviews");
            System.out.println("6. Logout");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> postNewJob(sc, companyId);
                case 2 -> viewPostedJobs(companyId);
                case 3 -> viewApplications(companyId);
                case 4 -> scheduleInterview(sc, companyId);
                case 5 -> viewScheduledInterviews(companyId);
                case 6 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 6);
    }

    public void postNewJob(Scanner sc, int companyId) {
        System.out.println("Enter Job Title: ");
        String title = sc.nextLine();
        System.out.println("Enter Job Description: ");
        String desc = sc.nextLine();
        System.out.println("Enter Salary: ");
        double salary = sc.nextDouble();
        System.out.println("Enter Total Openings: ");
        int openings = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Job Location: ");
        String location = sc.nextLine();
        System.out.println("Enter Job Type (Remote/Hybrid/On-site): ");
        String type = sc.nextLine();

        Job job = new Job(0, title, desc, salary, openings, LocalDateTime.now(), LocalDateTime.now().plusDays(30), location, type);
        job.setCompanyId(companyId);

        jobDAO.postJob(job);
        System.out.println("Job posted successfully.");
    }

    public void viewPostedJobs(int companyId) {
        List<Job> jobs = jobDAO.getJobsByCompanyId(companyId);
        if (jobs.isEmpty()) {
            System.out.println("No jobs posted yet.");
        } else {
            jobs.forEach(System.out::println);
        }
    }

    public void viewApplications(int companyId) {
        Map<Job, List<Candidate>> apps = applicationDAO.getApplicationsByCompany(companyId);
        if (apps.isEmpty()) {
            System.out.println("No applications found.");
        } else {
            for (Map.Entry<Job, List<Candidate>> entry : apps.entrySet()) {
                System.out.println("Job: " + entry.getKey().getJobTitle());
                for (Candidate c : entry.getValue()) {
                    System.out.println(" - " + c.getFullName() + " (" + c.getEmail() + ")");
                }
            }
        }
    }

    public void scheduleInterview(Scanner sc, int companyId) {
        List<Job> jobs = jobDAO.getJobsByCompanyId(companyId);
        if (jobs.isEmpty()) {
            System.out.println("No jobs posted.");
            return;
        }

        System.out.println("Select Job:");
        for (int i = 0; i < jobs.size(); i++) {
            System.out.println((i + 1) + ". " + jobs.get(i).getJobTitle());
        }

        int jobChoice = sc.nextInt() - 1;
        sc.nextLine();

        if (jobChoice < 0 || jobChoice >= jobs.size()) {
            System.out.println("Invalid job.");
            return;
        }

        Job selectedJob = jobs.get(jobChoice);
        List<Candidate> applicants = applicationDAO.getApplicantsForJob(selectedJob.getJobId());

        if (applicants.isEmpty()) {
            System.out.println("No applicants.");
            return;
        }

        for (int i = 0; i < applicants.size(); i++) {
            System.out.println((i + 1) + ". " + applicants.get(i).getFullName());
        }

        int candChoice = sc.nextInt() - 1;
        sc.nextLine();

        if (candChoice < 0 || candChoice >= applicants.size()) {
            System.out.println("Invalid candidate.");
            return;
        }

        Candidate selected = applicants.get(candChoice);
        Interview interview = new Random().nextBoolean() ? new TechnicalInterviewService() : new HRInterviewService();
        interview.setInterviewDatetime(LocalDateTime.now().plusDays(1));
        interview.setCandidateId(selected.getCandidateId());
        interview.setJobId(selectedJob.getJobId());

        interviewDAO.scheduleInterview(interview);

        System.out.println("Interview scheduled with " + selected.getFullName() + " on " + interview.getInterviewDatetime());
    }

    public void viewScheduledInterviews(int companyId) {
        List<Interview> interviews = interviewDAO.getInterviewsByCompany(companyId);
        if (interviews.isEmpty()) {
            System.out.println("No interviews scheduled.");
            return;
        }

        for (Interview interview : interviews) {
            System.out.println("Candidate ID: " + interview.getCandidateId());
            System.out.println("Job ID: " + interview.getJobId());
            System.out.println("Interview Type: " + interview.getClass().getSimpleName().replace("Service", ""));
            System.out.println("Interview Time: " + interview.getInterviewDatetime());
            System.out.println("------------------------");
        }
    }
}
