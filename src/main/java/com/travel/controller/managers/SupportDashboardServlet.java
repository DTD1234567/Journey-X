// File: src/main/java/com/travel/controller/managers/SupportDashboardServlet.java
package com.travel.controller.managers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.travel.model.CustomerSupportOfficer;

public class SupportDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if user is logged in as support officer
        if (session == null || session.getAttribute("user") == null ||
                !"supportOfficer".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        // Get support officer from session
        CustomerSupportOfficer officer = (CustomerSupportOfficer) session.getAttribute("user");

        // Set officer as request attribute
        request.setAttribute("officer", officer);

        // Forward to dashboard page
        request.getRequestDispatcher("/views/support-manager/dashboard.jsp").forward(request, response);
    }
}