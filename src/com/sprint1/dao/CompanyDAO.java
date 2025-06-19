package com.sprint1.dao;

import com.sprint1.model.Company;
import com.sprint1.util.DBUtil;
import com.sprint1.exception.company.*;

import java.sql.*;
import java.util.Set;

public class CompanyDAO {

  public Company getCompanyById(int companyId) {
    Company company = null;
    String query = "SELECT * FROM Company WHERE company_id = ?";

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)) {

      ps.setInt(1, companyId);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        company = new Company();
        company.setCompanyId(rs.getInt("company_id"));
        company.setCompanyName(rs.getString("company_name"));
        company.setIndustryType(rs.getString("industry_type"));
        company.setContactEmail(rs.getString("contact_email"));
        company.setContactPhone(rs.getString("contact_phone"));
        company.setOfficeAddress(rs.getString("office_address"));
      } else {
        throw new CompanyNotFoundException(companyId);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return company;
  }

  public Company getCompanyByEmail(String email) {
    Company company = null;
    String query = "SELECT * FROM Company WHERE contact_email = ?";

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)) {

      ps.setString(1, email);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        company = new Company();
        company.setCompanyId(rs.getInt("company_id"));
        company.setCompanyName(rs.getString("company_name"));
        company.setIndustryType(rs.getString("industry_type"));
        company.setContactEmail(rs.getString("contact_email"));
        company.setContactPhone(rs.getString("contact_phone"));
        company.setOfficeAddress(rs.getString("office_address"));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return company;
  }

  public void insertCompany(Company company) throws DuplicateCompanyEmailException {
    if (getCompanyByEmail(company.getContactEmail()) != null) {
      throw new DuplicateCompanyEmailException(company.getContactEmail());
    }

    String query = "INSERT INTO Company (company_name, industry_type, contact_email, contact_phone, office_address) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)) {

      ps.setString(1, company.getCompanyName());
      ps.setString(2, company.getIndustryType());
      ps.setString(3, company.getContactEmail());
      ps.setString(4, company.getContactPhone());
      ps.setString(5, company.getOfficeAddress());

      ps.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean validateCompanyLogin(int companyId, String email) {
    String query = "SELECT * FROM Company WHERE company_id = ? AND contact_email = ?";

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)) {

      ps.setInt(1, companyId);
      ps.setString(2, email);

      ResultSet rs = ps.executeQuery();
      if (!rs.next()) {
        throw new CompanyNotFoundException(companyId);
      }
      return true;

    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }
}
