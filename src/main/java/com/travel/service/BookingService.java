package com.travel.service;

import com.travel.dao.BookingDAO;
import com.travel.dao.PaymentDAO;
import com.travel.model.Booking;
import com.travel.model.Payment;
import java.util.Date;
import java.util.List;

public class BookingService {
    private BookingDAO bookingDAO;
    private PaymentDAO paymentDAO;

    public BookingService() {
        bookingDAO = new BookingDAO();
        paymentDAO = new PaymentDAO();
    }

    // Existing methods...

    public boolean createBooking(Booking booking) {
        if (booking.getCustomerId() <= 0 || booking.getPackageId() <= 0 || booking.getNumTravelers() <= 0) {
            return false;
        }
        if (booking.getBookingDate() == null) {
            return false;
        }
        if (booking.getConfirmationStatus() == null || booking.getConfirmationStatus().isEmpty()) {
            booking.setConfirmationStatus("Pending");
        }
        return bookingDAO.addBooking(booking);
    }

    public boolean processPayment(Payment payment) {
        if (payment.getCustomerId() <= 0 || payment.getBookingId() <= 0 || payment.getPackageId() <= 0) {
            return false;
        }
        if (payment.getAmount() <= 0 || payment.getMethod() == null || payment.getMethod().isEmpty()) {
            return false;
        }
        if (payment.getPaymentDate() == null) {
            payment.setPaymentDate(new Date());
        }
        if (payment.getStatus() == null || payment.getStatus().isEmpty()) {
            payment.setStatus("Pending");
        }
        if (payment.getCardNumber() == null || payment.getCardNumber().length() != 16 ||
                payment.getExpiryDate() == null || payment.getCvv() == null || payment.getCvv().length() != 3) {
            return false;
        }
        boolean paymentSuccess = paymentDAO.addPayment(payment);
        if (paymentSuccess) {
            return bookingDAO.updateBookingStatus(payment.getBookingId(), "Confirmed");
        }
        return false;
    }

    public Booking getBookingById(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }

    public boolean updateBooking(Booking booking) {
        if (booking.getBookingId() <= 0 || booking.getCustomerId() <= 0 || booking.getPackageId() <= 0) {
            return false;
        }
        if (booking.getBookingDate() == null || booking.getConfirmationStatus() == null || booking.getConfirmationStatus().isEmpty()) {
            return false;
        }
        return bookingDAO.updateBooking(booking);
    }

    public boolean cancelBooking(int bookingId) {
        return bookingDAO.updateBookingStatus(bookingId, "Cancelled");
    }

    public boolean confirmBooking(int bookingId) {
        return bookingDAO.updateBookingStatus(bookingId, "Confirmed");
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    public List<Booking> getBookingsByCustomerId(int customerId) {
        return bookingDAO.getBookingsByCustomerId(customerId);
    }

    // New methods for booking statistics
    public int getTotalBookings() {
        List<Booking> bookings = bookingDAO.getAllBookings();
        return bookings != null ? bookings.size() : 0;
    }

    public int getPendingBookingsCount() {
        List<Booking> bookings = bookingDAO.getAllBookings();
        if (bookings == null) return 0;
        return (int) bookings.stream()
                .filter(booking -> "Pending".equals(booking.getConfirmationStatus()))
                .count();
    }

    public int getConfirmedBookingsCount() {
        List<Booking> bookings = bookingDAO.getAllBookings();
        if (bookings == null) return 0;
        return (int) bookings.stream()
                .filter(booking -> "Confirmed".equals(booking.getConfirmationStatus()))
                .count();
    }

    public int getCancelledBookingsCount() {
        List<Booking> bookings = bookingDAO.getAllBookings();
        if (bookings == null) return 0;
        return (int) bookings.stream()
                .filter(booking -> "Cancelled".equals(booking.getConfirmationStatus()))
                .count();
    }
}