// File: src/main/java/com/travel/dao/CustomerSupportOfficerDAO.java
package com.travel.dao;

import com.travel.model.CustomerSupportOfficer;
import com.travel.util.DatabaseConnection;
import java.sql.*;

public class CustomerSupportOfficerDAO {

    // Validate customer support officer login
    public CustomerSupportOfficer validateCustomerSupportOfficer(String email, String password) {
        CustomerSupportOfficer officer = null;
        String sql = "SELECT * FROM CustomerSupportOfficer WHERE Email = ? AND Password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                officer = new CustomerSupportOfficer();
                officer.setEmployeeId(rs.getInt("EmployeeId"));
                officer.setName(rs.getString("Name"));
                officer.setJobTitle(rs.getString("JobTitle"));
                officer.setDepartment(rs.getString("Department"));
                officer.setContactNumber(rs.getString("ContactNumber"));
                officer.setEmail(rs.getString("Email"));
                officer.setPassword(rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return officer;
    }

    // Get customer support officer by ID
    public CustomerSupportOfficer getCustomerSupportOfficerById(int employeeId) {
        CustomerSupportOfficer officer = null;
        String sql = "SELECT * FROM CustomerSupportOfficer WHERE EmployeeId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                officer = new CustomerSupportOfficer();
                officer.setEmployeeId(rs.getInt("EmployeeId"));
                officer.setName(rs.getString("Name"));
                officer.setJobTitle(rs.getString("JobTitle"));
                officer.setDepartment(rs.getString("Department"));
                officer.setContactNumber(rs.getString("ContactNumber"));
                officer.setEmail(rs.getString("Email"));
                officer.setPassword(rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return officer;
    }

    // Get customer support officer by email
    public CustomerSupportOfficer getCustomerSupportOfficerByEmail(String email) {
        CustomerSupportOfficer officer = null;
        String sql = "SELECT * FROM CustomerSupportOfficer WHERE Email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                officer = new CustomerSupportOfficer();
                officer.setEmployeeId(rs.getInt("EmployeeId"));
                officer.setName(rs.getString("Name"));
                officer.setJobTitle(rs.getString("JobTitle"));
                officer.setDepartment(rs.getString("Department"));
                officer.setContactNumber(rs.getString("ContactNumber"));
                officer.setEmail(rs.getString("Email"));
                officer.setPassword(rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return officer;
    }

    // Add new customer support officer
    public boolean addCustomerSupportOfficer(CustomerSupportOfficer officer) {
        String sql = "INSERT INTO CustomerSupportOfficer (Name, JobTitle, Department, ContactNumber, Email, Password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, officer.getName());
            stmt.setString(2, officer.getJobTitle());
            stmt.setString(3, officer.getDepartment());
            stmt.setString(4, officer.getContactNumber());
            stmt.setString(5, officer.getEmail());
            stmt.setString(6, officer.getPassword());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update customer support officer
    public boolean updateCustomerSupportOfficer(CustomerSupportOfficer officer) {
        String sql = "UPDATE CustomerSupportOfficer SET Name = ?, JobTitle = ?, Department = ?, ContactNumber = ?, Email = ?, Password = ? WHERE EmployeeId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, officer.getName());
            stmt.setString(2, officer.getJobTitle());
            stmt.setString(3, officer.getDepartment());
            stmt.setString(4, officer.getContactNumber());
            stmt.setString(5, officer.getEmail());
            stmt.setString(6, officer.getPassword());
            stmt.setInt(7, officer.getEmployeeId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete customer support officer
    public boolean deleteCustomerSupportOfficer(int employeeId) {
        String sql = "DELETE FROM CustomerSupportOfficer WHERE EmployeeId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all customer support officers
    public java.util.List<CustomerSupportOfficer> getAllCustomerSupportOfficers() {
        java.util.List<CustomerSupportOfficer> officers = new java.util.ArrayList<>();
        String sql = "SELECT * FROM CustomerSupportOfficer";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CustomerSupportOfficer officer = new CustomerSupportOfficer();
                officer.setEmployeeId(rs.getInt("EmployeeId"));
                officer.setName(rs.getString("Name"));
                officer.setJobTitle(rs.getString("JobTitle"));
                officer.setDepartment(rs.getString("Department"));
                officer.setContactNumber(rs.getString("ContactNumber"));
                officer.setEmail(rs.getString("Email"));
                officer.setPassword(rs.getString("Password"));
                officers.add(officer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return officers;
    }
}