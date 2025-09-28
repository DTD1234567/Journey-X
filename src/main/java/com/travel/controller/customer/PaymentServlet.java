package com.travel.controller.customer;

import com.travel.dao.BookingDAO;
import com.travel.dao.PaymentDAO;
import com.travel.model.Booking;
import com.travel.model.Payment;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({
        "/customer/payments/history",
        "/customer/payments/make"
})
public class PaymentServlet extends HttpServlet {
    private PaymentDAO paymentDAO;
    private BookingDAO bookingDAO; // previously not initialized

    @Override
    public void init() throws ServletException {
        super.init();
        paymentDAO = new PaymentDAO();
        bookingDAO = new BookingDAO(); // initialize bookingDAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||
                !"customer".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        String pathInfo = request.getServletPath();

        switch (pathInfo) {
            case "/customer/payments/history":
                viewPaymentHistory(request, response);
                break;
            case "/customer/payments/make":
                showMakePaymentForm(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||
                !"customer".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        String pathInfo = request.getServletPath();

        switch (pathInfo) {
            case "/customer/payments/make":
                makePayment(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void viewPaymentHistory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int customerId = ((com.travel.model.Customer) session.getAttribute("user")).getCustomerId();

        List<Payment> payments = paymentDAO.getPaymentsByCustomerId(customerId);
        request.setAttribute("payments", payments);

        request.getRequestDispatcher("/views/customer/payments/history.jsp").forward(request, response);
    }

    private void showMakePaymentForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int customerId = ((com.travel.model.Customer) session.getAttribute("user")).getCustomerId();

        String bookingIdParam = request.getParameter("bookingId");
        Booking booking = null;

        if (bookingIdParam != null) {
            int bookingId = Integer.parseInt(bookingIdParam);
            booking = bookingDAO.getBookingById(bookingId);
        } else {
            List<Booking> bookings = bookingDAO.getBookingsByCustomerId(customerId);
            if (!bookings.isEmpty()) {
                booking = bookings.get(bookings.size() - 1); // pick latest
            }
        }

        if (booking == null) {
            request.setAttribute("errorMessage", "Booking not found. Please select a valid booking.");
        } else {
            request.setAttribute("booking", booking);
        }

        request.getRequestDispatcher("/views/customer/payments/make.jsp").forward(request, response);
    }


    private void makePayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int customerId = ((com.travel.model.Customer) session.getAttribute("user")).getCustomerId();

        String bookingIdParam = request.getParameter("bookingId");
        Booking booking = null;
        if (bookingIdParam != null) {
            try {
                int bookingId = Integer.parseInt(bookingIdParam);
                booking = bookingDAO.getBookingById(bookingId);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid booking ID");
            }
        }

        if (booking == null) {
            request.setAttribute("errorMessage", "Booking not found. Cannot proceed with payment.");
            showMakePaymentForm(request, response);
            return;
        }

        try {
            int amount = booking.getTotalPrice() > 0 ? (int) booking.getTotalPrice() : Integer.parseInt(request.getParameter("amount"));
            String method = request.getParameter("method");

            int paymentId = (int) (System.currentTimeMillis() % 1000000);

            Payment payment = new Payment();
            payment.setPaymentId(paymentId);
            payment.setPaymentDate(new Date());
            payment.setAmount(amount);
            payment.setMethod(method);
            payment.setStatus("Pending");
            payment.setCustomerId(customerId);

            boolean success = paymentDAO.addPayment(payment);

            if (success) {
                request.setAttribute("successMessage", "Payment submitted successfully. Your payment ID is: " + paymentId);
            } else {
                request.setAttribute("errorMessage", "Failed to process payment");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid amount format");
        }

        // Always forward to JSP instead of redirecting to avoid loops
        request.setAttribute("booking", booking);
        request.getRequestDispatcher("/views/customer/payments/make.jsp").forward(request, response);
    }
}
