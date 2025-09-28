// File: src/main/java/com/travel/dao/BookingManagerDAO.java
package com.travel.dao;

import com.travel.model.BookingManager;
import com.travel.util.DatabaseConnection;
import java.sql.*;

public class BookingManagerDAO {

    // Validate booking manager login
    public BookingManager validateBookingManager(String email, String password) {
        BookingManager manager = null;
        String sql = "SELECT * FROM BookingManager WHERE Email = ? AND Password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manager = new BookingManager();
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

    // Get booking manager by ID
    public BookingManager getBookingManagerById(int employeeId) {
        BookingManager manager = null;
        String sql = "SELECT * FROM BookingManager WHERE EmployeeId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manager = new BookingManager();
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

    // Get booking manager by email
    public BookingManager getBookingManagerByEmail(String email) {
        BookingManager manager = null;
        String sql = "SELECT * FROM BookingManager WHERE Email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manager = new BookingManager();
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

    // Add new booking manager
    public boolean addBookingManager(BookingManager manager) {
        String sql = "INSERT INTO BookingManager (Name, JobTitle, Department, ContactNumber, Email, Password) VALUES (?, ?, ?, ?, ?, ?)";

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

    // Update booking manager
    public boolean updateBookingManager(BookingManager manager) {
        String sql = "UPDATE BookingManager SET Name = ?, JobTitle = ?, Department = ?, ContactNumber = ?, Email = ? WHERE EmployeeId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manager.getName());
            stmt.setString(2, manager.getJobTitle());
            stmt.setString(3, manager.getDepartment());
            stmt.setString(4, manager.getContactNumber());
            stmt.setString(5, manager.getEmail());
            stmt.setInt(6, manager.getEmployeeId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete booking manager
    public boolean deleteBookingManager(int employeeId) {
        String sql = "DELETE FROM BookingManager WHERE EmployeeId = ?";

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

    // Get all booking managers
    public java.util.List<BookingManager> getAllBookingManagers() {
        java.util.List<BookingManager> managers = new java.util.ArrayList<>();
        String sql = "SELECT * FROM BookingManager";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                BookingManager manager = new BookingManager();
                manager.setEmployeeId(rs.getInt("EmployeeId"));
                manager.setName(rs.getString("Name"));
                manager.setJobTitle(rs.getString("JobTitle"));
                manager.setDepartment(rs.getString("Department"));
                manager.setContactNumber(rs.getString("ContactNumber"));
                manager.setEmail(rs.getString("Email"));
                managers.add(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return managers;
    }
}