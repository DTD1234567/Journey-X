// File: src/main/java/com/travel/controller/managers/BookingManagerDashboardServlet.java
package com.travel.controller.managers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.travel.model.BookingManager;

public class BookingManagerDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if user is logged in as booking manager
        if (session == null || session.getAttribute("user") == null ||
                !"bookingManager".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        // Get booking manager from session
        BookingManager manager = (BookingManager) session.getAttribute("user");

        // Set manager as request attribute
        request.setAttribute("manager", manager);

        // Forward to dashboard page
        request.getRequestDispatcher("/views/booking-manager/dashboard.jsp").forward(request, response);
    }
}