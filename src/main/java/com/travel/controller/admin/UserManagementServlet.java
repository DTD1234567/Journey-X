package com.travel.controller.admin;

import java.io.IOException;

import com.travel.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import com.travel.service.*;

public class UserManagementServlet extends HttpServlet {
    private SystemAdminService systemAdminService;
    private PackageManagerService packageManagerService;
    private BookingManagerService bookingManagerService;
    private PaymentManagerService paymentManagerService;
    private CustomerSupportService customerSupportService;

    @Override
    public void init() {
        systemAdminService = new SystemAdminService();
        packageManagerService = new PackageManagerService();
        bookingManagerService = new BookingManagerService();
        paymentManagerService = new PaymentManagerService();
        customerSupportService = new CustomerSupportService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (!isAdminLoggedIn(session)) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        request.setAttribute("admin", session.getAttribute("user"));
        String action = request.getParameter("action");
        if (action == null) action = "system-admins";

        switch (action) {
            case "customers":
                forward(request, response, "/views/system-admin/users/customers.jsp");
                break;
            case "package-managers":
                request.setAttribute("packageManagers", packageManagerService.getAllPackageManagers());
                forward(request, response, "/views/system-admin/users/package-managers.jsp");
                break;
            case "booking-managers":
                request.setAttribute("bookingManagers", bookingManagerService.getAllBookingManagers());
                forward(request, response, "/views/system-admin/users/booking-managers.jsp");
                break;
            case "payment-managers":
                request.setAttribute("paymentManagers", paymentManagerService.getAllPaymentManagers());
                forward(request, response, "/views/system-admin/users/payment-managers.jsp");
                break;
            case "support-officers":
                request.setAttribute("supportOfficers", customerSupportService.getAllCustomerSupportOfficers());
                forward(request, response, "/views/system-admin/users/support-officers.jsp");
                break;
            default: // system-admins
                request.setAttribute("admins", systemAdminService.getAllSystemAdmins());
                forward(request, response, "/views/system-admin/users/system-admins.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (!isAdminLoggedIn(session)) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        String action = request.getParameter("action");
        String redirectUrl = "/tour-system-admin-users?action=";
        String success = "&success=1";
        String error = "&error=";

        switch (action) {
            case "add-package-manager":
                if (handlePasswordValidation(request, response, redirectUrl + "package-managers")) return;
                redirectWithResult(request, response, redirectUrl + "package-managers",
                        systemAdminService.addPackageManager(createPackageManager(request)),
                        success, error + "Failed to add package manager");
                break;

            case "add-booking-manager":
                if (handlePasswordValidation(request, response, redirectUrl + "booking-managers")) return;
                redirectWithResult(request, response, redirectUrl + "booking-managers",
                        systemAdminService.addBookingManager(createBookingManager(request)),
                        success, error + "Failed to add booking manager");
                break;

            case "add-payment-manager":
                if (handlePasswordValidation(request, response, redirectUrl + "payment-managers")) return;
                redirectWithResult(request, response, redirectUrl + "payment-managers",
                        systemAdminService.addPaymentManager(createPaymentManager(request)),
                        success, error + "Failed to add payment manager");
                break;

            case "add-support-officer":
                if (handlePasswordValidation(request, response, redirectUrl + "support-officers")) return;
                redirectWithResult(request, response, redirectUrl + "support-officers",
                        customerSupportService.addCustomerSupportOfficer(createSupportOfficer(request)),
                        success, error + "Failed to add support officer");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/tour-system-admin-users");
                break;
        }
    }

    // ---------- Helpers ----------

    private boolean isAdminLoggedIn(HttpSession session) {
        return session != null && session.getAttribute("user") != null && "systemAdmin".equals(session.getAttribute("userType"));
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        req.getRequestDispatcher(path).forward(req, resp);
    }

    private boolean handlePasswordValidation(HttpServletRequest request, HttpServletResponse response, String redirectUrl) throws IOException {
        String password = request.getParameter("password");
        if (password == null || password.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + redirectUrl + "&error=Password is required");
            return true;
        }
        return false;
    }

    private void redirectWithResult(HttpServletRequest request, HttpServletResponse response,
                                    String baseUrl, boolean success,
                                    String successParam, String error) throws IOException {
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + baseUrl + (success ? successParam : error));
    }

    // ---------- Builders ----------

    private SystemAdmin createSystemAdmin(HttpServletRequest req) {
        SystemAdmin admin = new SystemAdmin();
        admin.setName(req.getParameter("name"));
        admin.setJobTitle(req.getParameter("jobTitle"));
        admin.setDepartment(req.getParameter("department"));
        admin.setContactNumber(req.getParameter("contactNumber"));
        admin.setEmail(req.getParameter("email"));
        admin.setPassword(req.getParameter("password"));
        return admin;
    }

    private PackageManager createPackageManager(HttpServletRequest req) {
        PackageManager pm = new PackageManager();
        pm.setName(req.getParameter("name"));
        pm.setJobTitle(req.getParameter("jobTitle"));
        pm.setDepartment(req.getParameter("department"));
        pm.setContactNumber(req.getParameter("contactNumber"));
        pm.setEmail(req.getParameter("email"));
        pm.setPassword(req.getParameter("password"));
        return pm;
    }

    private BookingManager createBookingManager(HttpServletRequest req) {
        BookingManager bm = new BookingManager();
        bm.setName(req.getParameter("name"));
        bm.setJobTitle(req.getParameter("jobTitle"));
        bm.setDepartment(req.getParameter("department"));
        bm.setContactNumber(req.getParameter("contactNumber"));
        bm.setEmail(req.getParameter("email"));
        bm.setPassword(req.getParameter("password"));
        return bm;
    }

    private PaymentManager createPaymentManager(HttpServletRequest req) {
        PaymentManager pym = new PaymentManager();
        pym.setName(req.getParameter("name"));
        pym.setJobTitle(req.getParameter("jobTitle"));
        pym.setDepartment(req.getParameter("department"));
        pym.setContactNumber(req.getParameter("contactNumber"));
        pym.setEmail(req.getParameter("email"));
        pym.setPassword(req.getParameter("password"));
        return pym;
    }

    private CustomerSupportOfficer createSupportOfficer(HttpServletRequest req) {
        CustomerSupportOfficer officer = new CustomerSupportOfficer();
        officer.setName(req.getParameter("name"));
        officer.setJobTitle(req.getParameter("jobTitle"));
        officer.setDepartment(req.getParameter("department"));
        officer.setContactNumber(req.getParameter("contactNumber"));
        officer.setEmail(req.getParameter("email"));
        officer.setPassword(req.getParameter("password"));
        return officer;
    }
}