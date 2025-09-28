// File: src/main/java/com/travel/controller/admin/SystemConfigurationServlet.java
package com.travel.controller.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.travel.model.SystemAdmin;
import com.travel.service.SystemAdminService;
import com.travel.model.PackageManager;
import com.travel.model.PaymentManager;
import com.travel.model.BookingManager;
import com.travel.model.CustomerSupportOfficer;


public class SystemConfigurationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SystemAdminService systemAdminService;

    public void init() {
        systemAdminService = new SystemAdminService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if user is logged in as system admin
        if (!isAdminLoggedIn(session)) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        // Get system admin from session
        SystemAdmin admin = (SystemAdmin) session.getAttribute("user");
        request.setAttribute("admin", admin);

        // Get the action parameter
        String action = request.getParameter("action");
        if (action == null) action = "system-messages";

        // Handle different actions
        switch (action) {
            case "system-messages":
                request.getRequestDispatcher("/views/system-admin/configuration/system-messages.jsp").forward(request, response);
                break;
            case "access-control":
                request.getRequestDispatcher("/views/system-admin/configuration/access-control.jsp").forward(request, response);
                break;
            case "role-assignment":
                // Get all users for display
                request.setAttribute("customers", systemAdminService.getAllCustomers());
                request.setAttribute("packageManagers", systemAdminService.getAllPackageManagers());
                request.setAttribute("bookingManagers", systemAdminService.getAllBookingManagers());
                request.setAttribute("paymentManagers", systemAdminService.getAllPaymentManagers());
                request.setAttribute("supportOfficers", systemAdminService.getAllCustomerSupportOfficers());

                request.getRequestDispatcher("/views/system-admin/configuration/role-assignment.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("/views/system-admin/configuration/system-messages.jsp").forward(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if user is logged in as system admin
        if (!isAdminLoggedIn(session)) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        // Get the action parameter
        String action = request.getParameter("action");

        // Handle different actions
        switch (action) {
            case "add-package-manager":
                // Get form parameters
                String pmName = request.getParameter("name");
                String pmJobTitle = request.getParameter("jobTitle");
                String pmDepartment = request.getParameter("department");
                String pmContactNumber = request.getParameter("contactNumber");
                String pmEmail = request.getParameter("email");
                String pmPassword = request.getParameter("password");

                // Create package manager
                PackageManager pm = new PackageManager();
                pm.setName(pmName);
                pm.setJobTitle(pmJobTitle);
                pm.setDepartment(pmDepartment);
                pm.setContactNumber(pmContactNumber);
                pm.setEmail(pmEmail);
                pm.setPassword(pmPassword);

                // Add package manager
                boolean pmSuccess = systemAdminService.addPackageManager(pm);

                if (pmSuccess) {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&success=Package manager added successfully");
                } else {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&error=Failed to add package manager");
                }
                break;

            case "add-booking-manager":
                // Get form parameters
                String bmName = request.getParameter("name");
                String bmJobTitle = request.getParameter("jobTitle");
                String bmDepartment = request.getParameter("department");
                String bmContactNumber = request.getParameter("contactNumber");
                String bmEmail = request.getParameter("email");
                String bmPassword = request.getParameter("password");


                // Create booking manager
                BookingManager bm = new BookingManager();
                bm.setName(bmName);
                bm.setJobTitle(bmJobTitle);
                bm.setDepartment(bmDepartment);
                bm.setContactNumber(bmContactNumber);
                bm.setEmail(bmEmail);
                bm.setPassword(bmPassword);


                // Add booking manager
                boolean bmSuccess = systemAdminService.addBookingManager(bm);

                if (bmSuccess) {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&success=Booking manager added successfully");
                } else {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&error=Failed to add booking manager");
                }
                break;

            case "add-payment-manager":
                System.out.println("Attempting to add payment manager...");  // DEBUG LOG
                // Get form parameters
                String pymName = request.getParameter("name");
                String pymJobTitle = request.getParameter("jobTitle");
                String pymDepartment = request.getParameter("department");
                String pymContactNumber = request.getParameter("contactNumber");
                String pymEmail = request.getParameter("email");
                String pymPassword = request.getParameter("password");
                System.out.println("Params: Name=" + pymName + ", Email=" + pymEmail);  // DEBUG LOG

                // Create payment manager
                PaymentManager pym = new PaymentManager();
                pym.setName(pymName);
                pym.setJobTitle(pymJobTitle);
                pym.setDepartment(pymDepartment);
                pym.setContactNumber(pymContactNumber);
                pym.setEmail(pymEmail);
                pym.setPassword(pymPassword);

                // Add payment manager
                boolean pymSuccess = systemAdminService.addPaymentManager(pym);
                System.out.println("Add result: " + pymSuccess);  // DEBUG LOG
                if (pymSuccess) {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&success=Payment manager added successfully");
                } else {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&error=Failed to add payment manager (check console for details)");
                }
                break;

            case "add-support-officer":
                // Get form parameters
                String csoName = request.getParameter("name");
                String csoJobTitle = request.getParameter("jobTitle");
                String csoDepartment = request.getParameter("department");
                String csoContactNumber = request.getParameter("contactNumber");
                String csoEmail = request.getParameter("email");
                String csoPassword = request.getParameter("password");

                // Create support officer
                CustomerSupportOfficer cso = new CustomerSupportOfficer();
                cso.setName(csoName);
                cso.setJobTitle(csoJobTitle);
                cso.setDepartment(csoDepartment);
                cso.setContactNumber(csoContactNumber);
                cso.setEmail(csoEmail);
                cso.setPassword(csoPassword);

                // Add support officer
                boolean csoSuccess = systemAdminService.addCustomerSupportOfficer(cso);

                if (csoSuccess) {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&success=Support officer added successfully");
                } else {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&error=Failed to add support officer");
                }
                break;

            case "delete-customer":
                // Get customer ID
                int customerId = Integer.parseInt(request.getParameter("customerId"));

                // Delete customer
                boolean customerDeleted = systemAdminService.deleteCustomer(customerId);

                if (customerDeleted) {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&success=Customer deleted successfully");
                } else {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&error=Failed to delete customer");
                }
                break;

            case "delete-package-manager":
                // Get package manager ID
                int pmId = Integer.parseInt(request.getParameter("employeeId"));

                // Delete package manager
                boolean pmDeleted = systemAdminService.deletePackageManager(pmId);

                if (pmDeleted) {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&success=Package manager deleted successfully");
                } else {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&error=Failed to delete package manager");
                }
                break;

            case "delete-booking-manager":
                // Get booking manager ID
                int bmId = Integer.parseInt(request.getParameter("employeeId"));

                // Delete booking manager
                boolean bmDeleted = systemAdminService.deleteBookingManager(bmId);

                if (bmDeleted) {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&success=Booking manager deleted successfully");
                } else {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&error=Failed to delete booking manager");
                }
                break;

            case "delete-payment-manager":
                // Get payment manager ID
                int pymId = Integer.parseInt(request.getParameter("employeeId"));

                // Delete payment manager
                boolean pymDeleted = systemAdminService.deletePaymentManager(pymId);

                if (pymDeleted) {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&success=Payment manager deleted successfully");
                } else {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&error=Failed to delete payment manager");
                }
                break;

            case "delete-support-officer":
                // Get support officer ID
                int csoId = Integer.parseInt(request.getParameter("employeeId"));

                // Delete support officer
                boolean csoDeleted = systemAdminService.deleteCustomerSupportOfficer(csoId);

                if (csoDeleted) {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&success=Support officer deleted successfully");
                } else {
                    response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration?action=role-assignment&error=Failed to delete support officer");
                }
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/tour-system-admin-configuration");
                break;
        }
    }

    private boolean isAdminLoggedIn(HttpSession session) {
        return session != null && session.getAttribute("user") != null &&
                "systemAdmin".equals(session.getAttribute("userType"));
    }
}