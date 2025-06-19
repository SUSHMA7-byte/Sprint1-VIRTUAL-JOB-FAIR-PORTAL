package com.sprint1.dao;

import com.sprint1.model.Interview;
import com.sprint1.service.interview.HRInterviewService;
import com.sprint1.service.interview.TechnicalInterviewService;
import com.sprint1.util.DBUtil;
import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;

public class InterviewDAO {

    public List<Interview> getInterviewsByCandidateId(int candidateId) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM Interview WHERE candidate_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, candidateId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int interviewId = rs.getInt("interview_id");
                LocalDateTime datetime = rs.getTimestamp("interview_datetime").toLocalDateTime();
                String resultStatus = rs.getString("result_status");
                String type = rs.getString("interview_type");

                Interview interview;
                if ("HR".equalsIgnoreCase(type)) {
                    interview = new HRInterviewService();
                } else {
                    interview = new TechnicalInterviewService();
                }

                interview.setInterviewId(interviewId);
                interview.setInterviewDatetime(datetime);
                interview.setResultStatus(resultStatus);
                interview.setInterviewType(type);

                interviews.add(interview);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return interviews;
    }

    public void scheduleInterview(Interview interview) {
        String sql = "INSERT INTO Interview (application_id, job_id, company_id, recruiter_id, interview_datetime, result_status, candidate_id, interview_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, interview.getApplicationId());
            ps.setInt(2, interview.getJobId());
            ps.setInt(3, interview.getCompanyId());
            ps.setInt(4, interview.getRecruiterId());
            ps.setTimestamp(5, Timestamp.valueOf(interview.getInterviewDatetime()));
            ps.setString(6, interview.getResultStatus());
            ps.setInt(7, interview.getCandidateId());
            ps.setString(8, interview.getInterviewType());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                interview.setInterviewId(rs.getInt(1));
            }

            System.out.println("Interview scheduled successfully with ID: " + interview.getInterviewId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Interview> getInterviewsByCompany(int companyId) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM Interview WHERE company_id = ? ORDER BY interview_datetime DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Interview interview;
                String type = rs.getString("interview_type");

                if ("HR".equalsIgnoreCase(type)) {
                    interview = new HRInterviewService();
                } else {
                    interview = new TechnicalInterviewService();
                }

                interview.setInterviewId(rs.getInt("interview_id"));
                interview.setCandidateId(rs.getInt("candidate_id"));
                interview.setJobId(rs.getInt("job_id"));
                interview.setCompanyId(rs.getInt("company_id"));
                interview.setInterviewDatetime(rs.getTimestamp("interview_datetime").toLocalDateTime());
                interview.setResultStatus(rs.getString("result_status"));

                interviews.add(interview);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return interviews;
    }



    public Interview getInterviewByCandidateAndJob(int candidateId, int jobId) {
        String query = "SELECT i.* FROM Interview i " +
                "JOIN Application a ON i.application_id = a.application_id " +
                "WHERE a.candidate_id = ? AND i.job_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, candidateId);
            stmt.setInt(2, jobId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Interview interview = new Interview() {
                    @Override
                    public void conductInterview() {

                    }
                };
                interview.setInterviewId(rs.getInt("interview_id"));
                interview.setCandidateId(candidateId);
                interview.setJobId(jobId);
                interview.setApplicationId(rs.getInt("application_id"));
                interview.setInterviewDatetime(rs.getTimestamp("interview_datetime").toLocalDateTime());
                interview.setResultStatus(rs.getString("result_status"));
                return interview;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}