package com.sprint1.testing;

import com.sprint1.model.Candidate;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

import com.sprint1.util.DBUtil;

public class TestMain {
    private Scanner sc;
    private final int THREAD_COUNT = 10;
    private int JOB_ID = 1;
    private List<Candidate> testCandidates; // ✅ Hold candidate list
    private Concurrency concurrency;

    public TestMain(Scanner sc) {
        this.sc = sc;
    }

    public void runFullTest() {
        deleteTestData();
        createTestCandidates();
        createTestApplications();
    }

    public void createTestCandidates() {
        System.out.println("Creating test candidates (multi-threaded)...");
        testCandidates = CandidateTesting.generateTestingCandidates(THREAD_COUNT);
        concurrency = new Concurrency(JOB_ID, THREAD_COUNT, testCandidates); // ✅ pass list
        concurrency.runCandidateCreationOnly();
    }

    public void createTestApplications() {
        System.out.println("Enter the job ID for which to create applications: ");
        int job_id = sc.nextInt();
        sc.nextLine();
        JOB_ID = job_id;
        if (testCandidates == null || testCandidates.isEmpty()) {
            System.out.println("No candidates available. Run candidate creation first.");
            return;
        }
        this.concurrency = new Concurrency(JOB_ID, THREAD_COUNT, testCandidates);
        System.out.println("Creating test applications (multi-threaded)...");
        concurrency.runApplicationCreationOnly();
    }

    public void deleteTestData() {
        System.out.println("Deleting all test candidates and their applications...");
        String deleteSQL = "DELETE FROM Candidate WHERE name LIKE ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {
            stmt.setString(1, "%test_candidate%");
            int deleted = stmt.executeUpdate();
            System.out.println("Deleted " + deleted + " test candidates and their related information.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while deleting test data.");
        }
        testCandidates = null; // ✅ clear after deletion
    }

    public void testHandler() {
        int choice;
        do {
            System.out.println("\n===== Testing =====");
            System.out.println("1. Run Full MultiThreading Test.");
            System.out.println("2. Create test Candidates.");
            System.out.println("3. Create test Applications.");
            System.out.println("4. Delete All Test Data from Database");
            System.out.println("5. Exit ...");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> runFullTest();
                case 2 -> createTestCandidates();
                case 3 -> createTestApplications();
                case 4 -> deleteTestData();
                default -> System.out.println("Invalid Choice ! Please Try Again...");
            }

        } while (choice != 5);
    }
}
