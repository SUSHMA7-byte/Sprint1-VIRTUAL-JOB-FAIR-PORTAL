package com.sprint1.dao;

import com.sprint1.model.Application;
import com.sprint1.model.Candidate;
import com.sprint1.model.Job;
import com.sprint1.util.DBUtil;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ApplicationDAO {

    // Apply a candidate to a job with validation (non-duplicate and valid status)
    public int applyForJob(int candidateId, int jobId, String status) {
        if (!List.of("Pending", "Reviewed", "Selected", "Rejected").contains(status)) {
            System.out.println("Invalid application status: " + status);
            return -1;
        }

        if (!new JobDAO().jobExists(jobId)) {
            System.out.println("Error: Job ID " + jobId + " does not exist. Cannot apply.");
            return -1;
        }

        if (checkApplicationExists(candidateId, jobId)) {
            System.out.println("Duplicate application: Candidate ID " + candidateId + " has already applied for Job ID " + jobId);
            return -1;
        }

        String sql = "INSERT INTO Application (candidate_id, job_id, application_status, application_date) VALUES (?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, candidateId);
            ps.setInt(2, jobId);
            ps.setString(3, status);
            ps.setDate(4, Date.valueOf(LocalDate.now()));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                    System.out.println("Application submitted successfully with ID: " + generatedId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedId;
    }


    // Check if the candidate has already applied for the job
    public boolean checkApplicationExists(int candidateId, int jobId) {
        String sql = "SELECT COUNT(*) FROM Application WHERE candidate_id = ? AND job_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, candidateId);
            ps.setInt(2, jobId);

            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get all applications for a specific job
    public List<Application> getApplicationsForJob(int jobId) {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT application_id, application_status, application_date FROM Application WHERE job_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Application app = new Application(
                        rs.getInt("application_id"),
                        rs.getString("application_status"),
                        rs.getDate("application_date").toLocalDate().atStartOfDay()
                );
                list.add(app);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get application ID based on candidate and job
    public int getApplicationId(int candidateId, int jobId) {
        String sql = "SELECT application_id FROM Application WHERE candidate_id = ? AND job_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, candidateId);
            ps.setInt(2, jobId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("application_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Get all jobs applied by a candidate
    public List<Job> getJobsAppliedByCandidate(int candidateId) {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT j.* FROM Job j JOIN Application a ON j.job_id = a.job_id WHERE a.candidate_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, candidateId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Job job = new Job(
                        rs.getInt("job_id"),
                        rs.getString("job_title"),
                        rs.getString("job_description"),
                        rs.getDouble("salary_package"),
                        rs.getInt("total_openings"),
                        rs.getDate("application_start_date").toLocalDate(),
                        rs.getDate("application_end_date").toLocalDate(),
                        rs.getString("job_location"),
                        rs.getString("job_type")
                );
                job.setCompanyId(rs.getInt("company_id"));
                jobs.add(job);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }

    // Get all applications grouped by job for a company
    public Map<Job, List<Candidate>> getApplicationsByCompany(int companyId) {
        Map<Job, List<Candidate>> applicationsMap = new HashMap<>();

        String sql = "SELECT j.*, c.* FROM Application a " +
                "JOIN Job j ON a.job_id = j.job_id " +
                "JOIN Candidate c ON a.candidate_id = c.candidate_id " +
                "WHERE j.company_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Job job = new Job(
                        rs.getInt("job_id"),
                        rs.getString("job_title"),
                        rs.getString("job_description"),
                        rs.getDouble("salary_package"),
                        rs.getInt("total_openings"),
                        rs.getDate("application_start_date").toLocalDate(),
                        rs.getDate("application_end_date").toLocalDate(),
                        rs.getString("job_location"),
                        rs.getString("job_type")
                );
                job.setCompanyId(rs.getInt("company_id"));

                Candidate candidate = new Candidate();
                candidate.setCandidateId(rs.getInt("candidate_id"));
                candidate.setFullName(rs.getString("name"));
                candidate.setEmail(rs.getString("email"));
                candidate.setPhoneNumber(rs.getString("phone"));
                candidate.setResumeLink(rs.getString("resume_link"));
                candidate.setCollege(rs.getString("college_name"));
                candidate.setCountry(rs.getString("country"));

                applicationsMap.computeIfAbsent(job, k -> new ArrayList<>()).add(candidate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicationsMap;
    }

    // Get list of applicants for a specific job
    public List<Candidate> getApplicantsForJob(int jobId) {
        List<Candidate> applicants = new ArrayList<>();
        String sql = "SELECT c.* FROM Candidate c " +
                "JOIN Application a ON c.candidate_id = a.candidate_id " +
                "WHERE a.job_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Candidate candidate = new Candidate();
                candidate.setCandidateId(rs.getInt("candidate_id"));
                candidate.setFullName(rs.getString("name"));
                candidate.setEmail(rs.getString("email"));
                candidate.setPhoneNumber(rs.getString("phone"));
                candidate.setResumeLink(rs.getString("resume_link"));
                candidate.setCollege(rs.getString("college_name"));
                candidate.setCountry(rs.getString("country"));

                applicants.add(candidate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicants;
    }

    // For testing: forcibly apply regardless of status or duplication
    public int testApplyForJob(int candidateId, int jobId, String status) {
        String sql = "INSERT INTO Application (candidate_id, job_id, application_status, application_date) VALUES (?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, candidateId);
            ps.setInt(2, jobId);
            ps.setString(3, status);
            ps.setDate(4, Date.valueOf(LocalDate.now()));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                    System.out.println("Application submitted successfully with ID: " + generatedId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedId;
    }
}
