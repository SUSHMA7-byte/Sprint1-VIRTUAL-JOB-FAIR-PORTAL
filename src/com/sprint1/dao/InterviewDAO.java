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
        String sql = "INSERT INTO Interview (candidate_id, job_id, company_id, interview_datetime, feedback, result_status, interview_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, interview.getCandidateId());
            ps.setInt(2, interview.getJobId());
            ps.setString(3, interview.getCompanyId());
            ps.setTimestamp(4, Timestamp.valueOf(interview.getInterviewDatetime()));
            ps.setString(6, interview.getResultStatus());

            String type = (interview instanceof HRInterviewService) ? "HR" : "Technical";
            ps.setString(7, type);

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
                interview.setCompanyId(rs.getString("company_id"));
                interview.setInterviewDatetime(rs.getTimestamp("interview_datetime").toLocalDateTime());
                interview.setResultStatus(rs.getString("result_status"));

                interviews.add(interview);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return interviews;
    }
}