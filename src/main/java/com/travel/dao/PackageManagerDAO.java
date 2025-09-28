// File: src/main/java/com/travel/dao/PackageManagerDAO.java
package com.travel.dao;

import com.travel.model.PackageManager;
import com.travel.util.DatabaseConnection;
import java.sql.*;

public class PackageManagerDAO {

    // Validate package manager login
    public PackageManager validatePackageManager(String email, String password) {
        PackageManager manager = null;
        String sql = "SELECT * FROM PackageManager WHERE Email = ? AND Password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manager = new PackageManager();
                manager.setEmployeeId(rs.getInt("EmployeeId"));
                manager.setName(rs.getString("Name"));
                manager.setJobTitle(rs.getString("JobTitle"));
                manager.setDepartment(rs.getString("Department"));
                manager.setContactNumber(rs.getString("ContactNumber"));
                manager.setEmail(rs.getString("Email"));
                manager.setPassword(rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return manager;
    }

    // Get package manager by ID
    public PackageManager getPackageManagerById(int employeeId) {
        PackageManager manager = null;
        String sql = "SELECT * FROM PackageManager WHERE EmployeeId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manager = new PackageManager();
                manager.setEmployeeId(rs.getInt("EmployeeId"));
                manager.setName(rs.getString("Name"));
                manager.setJobTitle(rs.getString("JobTitle"));
                manager.setDepartment(rs.getString("Department"));
                manager.setContactNumber(rs.getString("ContactNumber"));
                manager.setEmail(rs.getString("Email"));
                manager.setPassword(rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return manager;
    }

    // Get package manager by email
    public PackageManager getPackageManagerByEmail(String email) {
        PackageManager manager = null;
        String sql = "SELECT * FROM PackageManager WHERE Email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manager = new PackageManager();
                manager.setEmployeeId(rs.getInt("EmployeeId"));
                manager.setName(rs.getString("Name"));
                manager.setJobTitle(rs.getString("JobTitle"));
                manager.setDepartment(rs.getString("Department"));
                manager.setContactNumber(rs.getString("ContactNumber"));
                manager.setEmail(rs.getString("Email"));
                manager.setPassword(rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return manager;
    }

    // Add new package manager
    public boolean addPackageManager(PackageManager manager) {
        String sql = "INSERT INTO PackageManager (Name, JobTitle, Department, ContactNumber, Email, Password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manager.getName());
            stmt.setString(2, manager.getJobTitle());
            stmt.setString(3, manager.getDepartment());
            stmt.setString(4, manager.getContactNumber());
            stmt.setString(5, manager.getEmail());
            stmt.setString(6, manager.getPassword());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update package manager
    public boolean updatePackageManager(PackageManager manager) {
        String sql = "UPDATE PackageManager SET Name = ?, JobTitle = ?, Department = ?, ContactNumber = ?, Email = ?, Password = ? WHERE EmployeeId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manager.getName());
            stmt.setString(2, manager.getJobTitle());
            stmt.setString(3, manager.getDepartment());
            stmt.setString(4, manager.getContactNumber());
            stmt.setString(5, manager.getEmail());
            stmt.setString(6, manager.getPassword());
            stmt.setInt(7, manager.getEmployeeId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete package manager
    public boolean deletePackageManager(int employeeId) {
        String sql = "DELETE FROM PackageManager WHERE EmployeeId = ?";

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

    // Get all package managers
    public java.util.List<PackageManager> getAllPackageManagers() {
        java.util.List<PackageManager> managers = new java.util.ArrayList<>();
        String sql = "SELECT * FROM PackageManager";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PackageManager manager = new PackageManager();
                manager.setEmployeeId(rs.getInt("EmployeeId"));
                manager.setName(rs.getString("Name"));
                manager.setJobTitle(rs.getString("JobTitle"));
                manager.setDepartment(rs.getString("Department"));
                manager.setContactNumber(rs.getString("ContactNumber"));
                manager.setEmail(rs.getString("Email"));
                manager.setPassword(rs.getString("Password"));
                managers.add(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return managers;
    }
}