// File: src/main/java/com/travel/controller/admin/SystemOverviewServlet.java
package com.travel.controller.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.travel.model.SystemAdmin;

public class SystemOverviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if user is logged in as system admin
        if (session == null || session.getAttribute("user") == null ||
                !"systemAdmin".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        // Get system admin from session
        SystemAdmin admin = (SystemAdmin) session.getAttribute("user");

        // Set admin as request attribute
        request.setAttribute("admin", admin);

        // Get the action parameter
        String action = request.getParameter("action");

        if (action == null) {
            action = "bookings"; // Default action
        }

        // Handle different actions
        switch (action) {
            case "bookings":
                // Forward to bookings overview page
                request.getRequestDispatcher("/views/system-admin/overview/bookings.jsp").forward(request, response);
                break;
            case "revenue":
                // Forward to revenue overview page
                request.getRequestDispatcher("/views/system-admin/overview/revenue.jsp").forward(request, response);
                break;
            case "feedback":
                // Forward to feedback overview page
                request.getRequestDispatcher("/views/system-admin/overview/feedback.jsp").forward(request, response);
                break;
            case "pending-actions":
                // Forward to pending actions overview page
                request.getRequestDispatcher("/views/system-admin/overview/pending-actions.jsp").forward(request, response);
                break;
            default:
                // Forward to bookings overview page by default
                request.getRequestDispatcher("/views/system-admin/overview/bookings.jsp").forward(request, response);
                break;
        }
    }
}