package com.sprint1.dao;

import com.sprint1.model.Employee;
import com.sprint1.util.DBUtil;
import com.sprint1.exception.company.CompanyNotFoundException;
import com.sprint1.exception.employee.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeeDAO {

  public int addEmployee(Employee emp, int companyId) throws InvalidContactNumberException{
    validateContactNumber(emp.getContactNumber());
    validateCompanyExists(companyId);
    checkDuplicateEmail(emp.getEmail());

    String sql = "INSERT INTO Employee (full_name, role, contact_number, email, company_id) VALUES (?, ?, ?, ?, ?)";
    int generatedId = -1;

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      ps.setString(1, emp.getFullName());
      ps.setString(2, emp.getRole());
      ps.setString(3, emp.getContactNumber());
      ps.setString(4, emp.getEmail());
      ps.setInt(5, companyId);

      int rows = ps.executeUpdate();
      if (rows > 0) {
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
          generatedId = rs.getInt(1);
          System.out.println("Employee added successfully with ID: " + generatedId);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return generatedId;
  }

  public Employee getEmployeeById(int empId) {
    String sql = "SELECT * FROM Employee WHERE employee_id = ?";
    try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, empId);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return new Employee(
            rs.getInt("employee_id"),
            rs.getString("full_name"),
            rs.getString("role"),
            rs.getString("contact_number"),
            rs.getString("email"));
      } else {
        throw new EmployeeNotFoundException(empId);
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to fetch employee with ID " + empId);
    }
  }

  public List<Employee> getAllEmployeesByCompanyId(int companyId) {
    validateCompanyExists(companyId);

    List<Employee> list = new ArrayList<>();
    String sql = "SELECT * FROM Employee WHERE company_id = ?";

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, companyId);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Employee emp = new Employee(
            rs.getInt("employee_id"),
            rs.getString("full_name"),
            rs.getString("role"),
            rs.getString("contact_number"),
            rs.getString("email"));
        list.add(emp);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public void deleteEmployee(int empId) {
    getEmployeeById(empId);

    String sql = "DELETE FROM Employee WHERE employee_id = ?";

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, empId);
      ps.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void validateContactNumber(String number) throws InvalidContactNumberException{
    if (number == null || number.length() < 10 || number.length() > 15) {
      throw new InvalidContactNumberException(number);
    }
  }

  private void checkDuplicateEmail(String email) {
    String sql = "SELECT employee_id FROM Employee WHERE email = ?";
    try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, email);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        throw new DuplicateEmployeeEmailException(email);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void validateCompanyExists(int companyId) {
    String sql = "SELECT company_id FROM Company WHERE company_id = ?";
    try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, companyId);
      ResultSet rs = ps.executeQuery();

      if (!rs.next()) {
        throw new CompanyNotFoundException(companyId);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
