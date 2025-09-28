// File: src/main/java/com/travel/service/BookingManagerService.java
package com.travel.service;

import com.travel.dao.BookingManagerDAO;
import com.travel.model.BookingManager;
import java.util.List;

public class BookingManagerService {
    private BookingManagerDAO bookingManagerDAO;

    public BookingManagerService() {
        bookingManagerDAO = new BookingManagerDAO();
    }

    // Authenticate booking manager login
    public BookingManager authenticateBookingManager(String email, String password) {
        return bookingManagerDAO.validateBookingManager(email, password);
    }

    // Get booking manager by ID
    public BookingManager getBookingManagerById(int employeeId) {
        return bookingManagerDAO.getBookingManagerById(employeeId);
    }

    // Get booking manager by email
    public BookingManager getBookingManagerByEmail(String email) {
        return bookingManagerDAO.getBookingManagerByEmail(email);
    }

    // Add new booking manager
    public boolean addBookingManager(BookingManager manager) {
        // Check if email already exists
        BookingManager existingManager = bookingManagerDAO.getBookingManagerByEmail(manager.getEmail());
        if (existingManager != null) {
            return false; // Email already exists
        }

        return bookingManagerDAO.addBookingManager(manager);
    }

    // Update booking manager
    public boolean updateBookingManager(BookingManager manager) {
        return bookingManagerDAO.updateBookingManager(manager);
    }

    // Delete booking manager
    public boolean deleteBookingManager(int employeeId) {
        return bookingManagerDAO.deleteBookingManager(employeeId);
    }

    // Get all booking managers
    public List<BookingManager> getAllBookingManagers() {
        return bookingManagerDAO.getAllBookingManagers();
    }
}