package com.sprint1.dao;

import com.sprint1.model.Job;
import com.sprint1.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    public void postJob(Job job) {
        String sql = "INSERT INTO Job (job_title, job_description, salary_package, total_openings, application_start_date, application_end_date, job_location, job_type, company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, job.getJobTitle());
            ps.setString(2, job.getJobDescription());
            ps.setDouble(3, job.getSalaryPackage());
            ps.setInt(4, job.getTotalOpenings());
            ps.setTimestamp(5, Timestamp.valueOf(job.getApplicationStartDate()));
            ps.setTimestamp(6, Timestamp.valueOf(job.getApplicationEndDate()));
            ps.setString(7, job.getJobLocation());
            ps.setString(8, job.getJobType());
            ps.setInt(9, job.getCompanyId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                job.setJobId(rs.getInt(1)); // set generated job ID back to model
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Job> getJobsByCompanyId(int companyId) {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM Job WHERE company_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getInt("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setJobDescription(rs.getString("job_description"));
                job.setSalaryPackage(rs.getDouble("salary_package"));
                job.setTotalOpenings(rs.getInt("total_openings"));
                job.setApplicationStartDate(rs.getTimestamp("application_start_date").toLocalDateTime());
                job.setApplicationStartDate(rs.getTimestamp("application_end_date").toLocalDateTime());
                job.setJobLocation(rs.getString("job_location"));
                job.setJobType(rs.getString("job_type"));
                job.setCompanyId(rs.getInt("company_id"));
                jobs.add(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jobs;
    }

    public List<Job> getAllActiveJobs() {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM Job WHERE CURDATE() BETWEEN application_start_date AND application_end_date";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getInt("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setJobDescription(rs.getString("job_description"));
                job.setSalaryPackage(rs.getDouble("salary_package"));
                job.setTotalOpenings(rs.getInt("total_openings"));
                job.setApplicationStartDate(rs.getTimestamp("application_start_date").toLocalDateTime());
                job.setApplicationEndDate(rs.getTimestamp("application_end_date").toLocalDateTime());
                job.setJobLocation(rs.getString("job_location"));
                job.setJobType(rs.getString("job_type"));
                job.setCompanyId(rs.getInt("company_id"));
                jobs.add(job);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jobs;
    }

    public Job getJobById(int jobId) {
        String sql = "SELECT * FROM Job WHERE job_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Job job = new Job(
                        rs.getInt("job_id"),
                        rs.getString("job_title"),
                        rs.getString("job_description"),
                        rs.getDouble("salary_package"),
                        rs.getInt("total_openings"),
                        rs.getTimestamp("application_start_date").toLocalDateTime(),
                        rs.getTimestamp("application_end_date").toLocalDateTime(),
                        rs.getString("job_location"),
                        rs.getString("job_type")
                );
                job.setCompanyId(rs.getInt("company_id"));
                return job;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
