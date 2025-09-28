// File: src/main/java/com/travel/dao/PaymentManagerDAO.java
package com.travel.dao;

import com.travel.model.PaymentManager;
import com.travel.util.DatabaseConnection;
import java.sql.*;

public class PaymentManagerDAO {

    // Validate payment manager login
    public PaymentManager validatePaymentManager(String email, String password) {
        PaymentManager manager = null;
        String sql = "SELECT * FROM PaymentManager WHERE Email = ? AND Password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manager = new PaymentManager();
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

    // Get payment manager by ID
    public PaymentManager getPaymentManagerById(int employeeId) {
        PaymentManager manager = null;
        String sql = "SELECT * FROM PaymentManager WHERE EmployeeId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manager = new PaymentManager();
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

    // Get payment manager by email
    public PaymentManager getPaymentManagerByEmail(String email) {
        PaymentManager manager = null;
        String sql = "SELECT * FROM PaymentManager WHERE Email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manager = new PaymentManager();
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

    // Add new payment manager
    public boolean addPaymentManager(PaymentManager manager) {
        String sql = "INSERT INTO PaymentManager (Name, JobTitle, Department, ContactNumber, Email, Password) VALUES (?, ?, ?, ?, ?, ?)";

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

    // Update payment manager
    public boolean updatePaymentManager(PaymentManager manager) {
        String sql = "UPDATE PaymentManager SET Name = ?, JobTitle = ?, Department = ?, ContactNumber = ?, Email = ?, Password = ? WHERE EmployeeId = ?";

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

    // Delete payment manager
    public boolean deletePaymentManager(int employeeId) {
        String sql = "DELETE FROM PaymentManager WHERE EmployeeId = ?";

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

    // Get all payment managers
    public java.util.List<PaymentManager> getAllPaymentManagers() {
        java.util.List<PaymentManager> managers = new java.util.ArrayList<>();
        String sql = "SELECT * FROM PaymentManager";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PaymentManager manager = new PaymentManager();
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