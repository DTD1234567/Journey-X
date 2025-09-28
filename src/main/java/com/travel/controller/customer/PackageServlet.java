package com.travel.controller.customer;

import com.travel.service.PackageService;
import com.travel.model.Package;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

// Remove @WebServlet annotation
public class PackageServlet extends HttpServlet {
    private PackageService packageService;

    @Override
    public void init() throws ServletException {
        super.init();
        packageService = new PackageService();
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

        String pathInfo = request.getPathInfo() != null ? request.getPathInfo() : "";
        String action = pathInfo.isEmpty() ? "" : pathInfo.substring(1);

        switch (action) {
            case "list":
                List<Package> packages = packageService.getAllPackages();
                request.setAttribute("packages", packages);
                request.getRequestDispatcher("/views/customer/packages/list.jsp").forward(request, response);
                break;
            case "details":
                String idParam = request.getParameter("id");
                try {
                    int packageId = Integer.parseInt(idParam);
                    Package pkg = packageService.getPackageById(packageId);
                    if (pkg != null) {
                        request.setAttribute("package", pkg);
                        request.getRequestDispatcher("/views/customer/packages/details.jsp").forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/tour-customer-packages/list?error=Package+not+found");
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/tour-customer-packages/list?error=Invalid+package+ID");
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}