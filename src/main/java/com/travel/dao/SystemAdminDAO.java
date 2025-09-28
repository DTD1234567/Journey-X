// File: src/main/java/com/travel/dao/SystemAdminDAO.java
package com.travel.dao;

import com.travel.model.SystemAdmin;
import com.travel.util.DatabaseConnection;
import java.sql.*;

public class SystemAdminDAO {

    // Validate system admin login
    public SystemAdmin validateSystemAdmin(String email, String password) {
        SystemAdmin admin = null;
        String sql = "SELECT * FROM SystemAdmin WHERE Email = ? AND Password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new SystemAdmin();
                admin.setEmployeeId(rs.getInt("EmployeeId"));
                admin.setName(rs.getString("Name"));
                admin.setJobTitle(rs.getString("JobTitle"));
                admin.setDepartment(rs.getString("Department"));
                admin.setContactNumber(rs.getString("ContactNumber"));
                admin.setEmail(rs.getString("Email"));
                admin.setPassword(rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    // Get system admin by ID
    public SystemAdmin getSystemAdminById(int employeeId) {
        SystemAdmin admin = null;
        String sql = "SELECT * FROM SystemAdmin WHERE EmployeeId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new SystemAdmin();
                admin.setEmployeeId(rs.getInt("EmployeeId"));
                admin.setName(rs.getString("Name"));
                admin.setJobTitle(rs.getString("JobTitle"));
                admin.setDepartment(rs.getString("Department"));
                admin.setContactNumber(rs.getString("ContactNumber"));
                admin.setEmail(rs.getString("Email"));
                admin.setPassword(rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    // Get system admin by email
    public SystemAdmin getSystemAdminByEmail(String email) {
        SystemAdmin admin = null;
        String sql = "SELECT * FROM SystemAdmin WHERE Email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new SystemAdmin();
                admin.setEmployeeId(rs.getInt("EmployeeId"));
                admin.setName(rs.getString("Name"));
                admin.setJobTitle(rs.getString("JobTitle"));
                admin.setDepartment(rs.getString("Department"));
                admin.setContactNumber(rs.getString("ContactNumber"));
                admin.setEmail(rs.getString("Email"));
                admin.setPassword(rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    // Add new system admin
    /*public boolean addSystemAdmin(SystemAdmin admin) {
        String sql = "INSERT INTO SystemAdmin (Name, JobTitle, Department, ContactNumber, Email, Password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getJobTitle());
            stmt.setString(3, admin.getDepartment());
            stmt.setString(4, admin.getContactNumber());
            stmt.setString(5, admin.getEmail());
            stmt.setString(6, admin.getPassword());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/

    // Update system admin
    public boolean updateSystemAdmin(SystemAdmin admin) {
        String sql = "UPDATE SystemAdmin SET Name = ?, JobTitle = ?, Department = ?, ContactNumber = ?, Email = ?, Password = ? WHERE EmployeeId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getJobTitle());
            stmt.setString(3, admin.getDepartment());
            stmt.setString(4, admin.getContactNumber());
            stmt.setString(5, admin.getEmail());
            stmt.setString(6, admin.getPassword());
            stmt.setInt(7, admin.getEmployeeId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete system admin
    /*public boolean deleteSystemAdmin(int employeeId) {
        String sql = "DELETE FROM SystemAdmin WHERE EmployeeId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/

    // Get all system admins
    public java.util.List<SystemAdmin> getAllSystemAdmins() {
        java.util.List<SystemAdmin> admins = new java.util.ArrayList<>();
        String sql = "SELECT * FROM SystemAdmin";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SystemAdmin admin = new SystemAdmin();
                admin.setEmployeeId(rs.getInt("EmployeeId"));
                admin.setName(rs.getString("Name"));
                admin.setJobTitle(rs.getString("JobTitle"));
                admin.setDepartment(rs.getString("Department"));
                admin.setContactNumber(rs.getString("ContactNumber"));
                admin.setEmail(rs.getString("Email"));
                admin.setPassword(rs.getString("Password"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }
}