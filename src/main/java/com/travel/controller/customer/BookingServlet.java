package com.travel.controller.customer;

import com.travel.dao.BookingDAO;
import com.travel.dao.PackageDAO;
import com.travel.model.Booking;
import com.travel.model.Package;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Servlet for handling booking operations
@WebServlet({
        "/customer/bookings/create",
        "/customer/bookings/history",
        "/customer/bookings/status",
        "/customer/bookings/cancel"
})
public class BookingServlet extends HttpServlet {
    private BookingDAO bookingDAO;
    private PackageDAO packageDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        bookingDAO = new BookingDAO();
        packageDAO = new PackageDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is logged in and is a customer
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||
                !"customer".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        String pathInfo = request.getServletPath();

        switch (pathInfo) {
            case "/customer/bookings/create":
                showCreateBookingForm(request, response);
                break;
            case "/customer/bookings/history":
                viewBookingHistory(request, response);
                break;
            case "/customer/bookings/status":
                viewBookingStatus(request, response);
                break;
            case "/customer/bookings/cancel":
                showCancelBookingForm(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is logged in and is a customer
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||
                !"customer".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        String pathInfo = request.getServletPath();

        switch (pathInfo) {
            case "/customer/bookings/create":
                createBooking(request, response);
                break;
            case "/customer/bookings/cancel":
                cancelBooking(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // Show create booking form
    private void showCreateBookingForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pId = request.getParameter("packageId");

        if (pId != null && !pId.isEmpty()) {
            int packageId = Integer.parseInt(pId);
            Package tourPackage = packageDAO.getPackageById(packageId);
            if (tourPackage != null) {
                request.setAttribute("package", tourPackage);
                // Set initial total price for 1 traveler (optional, for display)
                double initialTotalPrice = tourPackage.getPrice() * 1;
                request.setAttribute("initialTotalPrice", initialTotalPrice);
            } else {
                request.setAttribute("errorMessage", "Package not found.");
            }
        } else {
            request.setAttribute("errorMessage", "No package selected.");
        }

        request.getRequestDispatcher("/views/customer/bookings/create.jsp").forward(request, response);
    }

    // Create a new booking
    private void createBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        com.travel.model.Customer customer = (com.travel.model.Customer) session.getAttribute("user");

        int customerId = customer.getCustomerId();
        int packageId = Integer.parseInt(request.getParameter("packageId"));
        int numTravelers = Integer.parseInt(request.getParameter("numTravelers"));
        String bookingDateStr = request.getParameter("bookingDate");

        // Get package & price
        Package tourPackage = packageDAO.getPackageById(packageId);
        double totalPrice = tourPackage.getPrice() * numTravelers;

        // Convert date
        java.sql.Date bookingDate = java.sql.Date.valueOf(bookingDateStr);

        // Create booking
        Booking booking = new Booking();
        booking.setCustomerId(customerId);
        booking.setPackageId(packageId);
        booking.setBookingDate(bookingDate);
        booking.setNumTravelers(numTravelers);
        booking.setTotalPrice(totalPrice);
        booking.setConfirmationStatus("Pending");

        // Save booking
        if (bookingDAO.addBooking(booking)) {
            int bookingId = booking.getBookingId();
            request.setAttribute("successMessage", "Booking created! ID: " + bookingId + ", Total: $" + totalPrice);
            request.setAttribute("booking", booking); // Pass booking to payment page
            request.getRequestDispatcher("/views/customer/payments/make.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Failed to create booking");
            showCreateBookingForm(request, response);
        }
    }


    // View booking history
    private void viewBookingHistory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int customerId = ((com.travel.model.Customer) session.getAttribute("user")).getCustomerId();

        List<Booking> bookings = bookingDAO.getBookingsByCustomerId(customerId);
        request.setAttribute("bookings", bookings);

        request.getRequestDispatcher("/views/customer/bookings/history.jsp").forward(request, response);
    }

    // View booking status
    private void viewBookingStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int customerId = ((com.travel.model.Customer) session.getAttribute("user")).getCustomerId();

        List<Booking> bookings = bookingDAO.getBookingsByCustomerId(customerId);
        request.setAttribute("bookings", bookings);

        request.getRequestDispatcher("/views/customer/bookings/status.jsp").forward(request, response);
    }

    // Show cancel booking form
    private void showCancelBookingForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int customerId = ((com.travel.model.Customer) session.getAttribute("user")).getCustomerId();

        List<Booking> bookings = bookingDAO.getBookingsByCustomerId(customerId);
        request.setAttribute("bookings", bookings);

        request.getRequestDispatcher("/views/customer/bookings/cancel.jsp").forward(request, response);
    }

    // Cancel a booking
    private void cancelBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));

        boolean success = bookingDAO.updateBookingStatus(bookingId, "Cancelled");

        if (success) {
            request.setAttribute("successMessage", "Booking cancelled successfully");
        } else {
            request.setAttribute("errorMessage", "Failed to cancel booking");
        }

        showCancelBookingForm(request, response);
    }
}