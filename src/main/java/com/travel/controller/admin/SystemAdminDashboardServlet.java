// File: src/main/java/com/travel/controller/admin/SystemAdminDashboardServlet.java
package com.travel.controller.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.travel.model.SystemAdmin;

public class SystemAdminDashboardServlet extends HttpServlet {
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

        // Forward to dashboard page
        request.getRequestDispatcher("/views/system-admin/dashboard.jsp").forward(request, response);
    }
}