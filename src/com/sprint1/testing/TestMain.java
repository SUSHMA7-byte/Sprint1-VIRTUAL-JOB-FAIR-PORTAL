package com.sprint1.testing;

import com.sprint1.model.Candidate;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

import com.sprint1.util.DBUtil;

public class TestMain {
    private Scanner sc;
    private Concurrency concurrency;
    private final int THREAD_COUNT = 10;
    private int JOB_ID = 1;


    public void runFullTest() {
        deleteTestData();
        createTestCandidates();
        createTestApplications();
    }

    public void createTestCandidates() {
        System.out.println("Creating test candidates (multi-threaded)...");
        concurrency.runCandidateCreationOnly();
    }

    public void createTestApplications() {
        System.out.println("Enter the job ID for which to create applications: ");
        int job_id = sc.nextInt();
        sc.nextLine();
        JOB_ID = job_id;
        System.out.println("Creating test applications (multi-threaded)...");
        concurrency.runApplicationCreationOnly();
    }

    public void deleteTestData() {
        System.out.println("Deleting all test candidates and their applications...");

        String deleteSQL = "DELETE FROM Candidate WHERE name LIKE ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(deleteSQL)
        ) {
            stmt.setString(1, "%test_candidate%");
            int deleted = stmt.executeUpdate();
            System.out.println("Deleted " + deleted + " test candidates and their related information.");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while deleting test data.");
        }
    }

    public TestMain(Scanner sc) {
        this.sc = sc;
        this.concurrency = new Concurrency(JOB_ID, THREAD_COUNT);
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
                case 1:
                    runFullTest();
                    break;
                case 2:
                    createTestCandidates();
                    break;
                case 3:
                    createTestApplications();
                    break;
                case 4:
                    deleteTestData();
                    break;
                default:
                    System.out.println("Invalid Choice ! Please Try Again...");
            }

        } while (choice != 5);
    }


}
