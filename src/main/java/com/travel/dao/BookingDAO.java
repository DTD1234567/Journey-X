package com.travel.dao;

import com.travel.model.Booking;
import com.travel.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Data Access Object for Booking entity
public class BookingDAO {

    // Add a new booking to the database
    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO Booking (BookingDate, ConfirmationStatus, CustomerId, PackageId, NumTravelers, TotalPrice) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setDate(1, new java.sql.Date(booking.getBookingDate().getTime()));
            pstmt.setString(2, booking.getConfirmationStatus());
            pstmt.setInt(3, booking.getCustomerId());
            pstmt.setInt(4, booking.getPackageId());
            pstmt.setInt(5, booking.getNumTravelers());
            pstmt.setDouble(6, booking.getTotalPrice());
            int rowsAffected = pstmt.executeUpdate();

            // If insertion was successful, get the generated booking ID
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        booking.setBookingId(generatedKeys.getInt(1));
                    }
                }
            }

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get booking by ID
    public Booking getBookingById(int bookingId) {
        String sql = "SELECT * FROM Booking WHERE BookingId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookingId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("BookingId"));
                booking.setBookingDate(rs.getDate("BookingDate"));
                booking.setConfirmationStatus(rs.getString("ConfirmationStatus"));
                booking.setCustomerId(rs.getInt("CustomerId"));
                return booking;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update booking information
    public boolean updateBooking(Booking booking) {
        String sql = "UPDATE Booking SET BookingDate = ?, ConfirmationStatus = ?, CustomerId = ? WHERE BookingId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, new java.sql.Date(booking.getBookingDate().getTime()));
            pstmt.setString(2, booking.getConfirmationStatus());
            pstmt.setInt(3, booking.getCustomerId());
            pstmt.setInt(4, booking.getBookingId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a booking
    public boolean deleteBooking(int bookingId) {
        String sql = "DELETE FROM Booking WHERE BookingId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookingId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("BookingId"));
                booking.setBookingDate(rs.getDate("BookingDate"));
                booking.setConfirmationStatus(rs.getString("ConfirmationStatus"));
                booking.setCustomerId(rs.getInt("CustomerId"));
                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Get bookings by customer ID
    public List<Booking> getBookingsByCustomerId(int customerId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE CustomerId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("BookingId"));
                booking.setBookingDate(rs.getDate("BookingDate"));
                booking.setConfirmationStatus(rs.getString("ConfirmationStatus"));
                booking.setCustomerId(rs.getInt("CustomerId"));
                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Update booking status
    public boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE Booking SET ConfirmationStatus = ? WHERE BookingId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, bookingId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}