// File: src/main/java/com/travel/controller/managers/PackageManagementServlet.java
package com.travel.controller.managers;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.travel.model.PackageManager;
import com.travel.model.Package;
import com.travel.service.PackageService;

public class PackageManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PackageService packageService;

    public void init() {
        packageService = new PackageService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if user is logged in as package manager
        if (session == null || session.getAttribute("user") == null ||
                !"packageManager".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        // Get package manager from session
        PackageManager manager = (PackageManager) session.getAttribute("user");
        request.setAttribute("manager", manager);

        // Get the action parameter
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Default action
        }

        // Handle different actions
        switch (action) {
            case "add":
                // Forward to add package page
                request.getRequestDispatcher("/views/package-manager/packages/add.jsp").forward(request, response);
                break;
            case "list":
                // Get all packages
                List<Package> packages = packageService.getAllPackages();
                request.setAttribute("packages", packages);

                // Forward to list packages page
                request.getRequestDispatcher("/views/package-manager/packages/list.jsp").forward(request, response);
                break;
            case "edit":
                // Get package ID
                String packageId = request.getParameter("id");

                // Get package details
                Package pkg = packageService.getPackageById(Integer.parseInt(packageId));
                request.setAttribute("package", pkg);

                // Forward to edit package page
                request.getRequestDispatcher("/views/package-manager/packages/edit.jsp").forward(request, response);
                break;
            case "remove":
                // Get package ID
                String removeId = request.getParameter("id");

                // Get package details
                Package removePkg = packageService.getPackageById(Integer.parseInt(removeId));
                request.setAttribute("package", removePkg);

                // Forward to remove package page
                request.getRequestDispatcher("/views/package-manager/packages/remove.jsp").forward(request, response);
                break;
            default:
                // Forward to list packages page by default
                List<Package> defaultPackages = packageService.getAllPackages();
                request.setAttribute("packages", defaultPackages);
                request.getRequestDispatcher("/views/package-manager/packages/list.jsp").forward(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if user is logged in as package manager
        if (session == null || session.getAttribute("user") == null ||
                !"packageManager".equals(session.getAttribute("userType"))) {
            response.sendRedirect(request.getContextPath() + "/tour-login");
            return;
        }

        // Get the action parameter
        String action = request.getParameter("action");

        // Handle different actions
        switch (action) {
            case "add":
                // Get form parameters
                String packageName = request.getParameter("packageName");
                double price = Double.parseDouble(request.getParameter("price"));
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                String duration = request.getParameter("duration");
                String destinations = request.getParameter("destinations");
                String description = request.getParameter("description");

                // Create package object
                Package pkg = new Package();
                pkg.setPackageName(packageName);
                pkg.setPrice(price);
                // Convert String to java.sql.Date
                pkg.setStartDate(java.sql.Date.valueOf(startDate));
                pkg.setEndDate(java.sql.Date.valueOf(endDate));
                pkg.setDuration(duration);
                pkg.setDestinations(destinations);
                pkg.setDescription(description);

                // Add package
                boolean success = packageService.addPackage(pkg);

                if (success) {
                    response.sendRedirect(request.getContextPath() + "/tour-package-manager-packages?action=list&success=1");
                } else {
                    response.sendRedirect(request.getContextPath() + "/tour-package-manager-packages?action=add&error=1");
                }
                break;

            case "update":
                // Get form parameters
                int updateId = Integer.parseInt(request.getParameter("packageId"));
                String updatePackageName = request.getParameter("packageName");
                double updatePrice = Double.parseDouble(request.getParameter("price"));
                String updateStartDate = request.getParameter("startDate");
                String updateEndDate = request.getParameter("endDate");
                String updateDuration = request.getParameter("duration");
                String updateDestinations = request.getParameter("destinations");
                String updateDescription = request.getParameter("description");

                // Create package object
                Package updatePkg = new Package();
                updatePkg.setPackageId(updateId);
                updatePkg.setPackageName(updatePackageName);
                updatePkg.setPrice(updatePrice);
                // Convert String to java.sql.Date
                updatePkg.setStartDate(java.sql.Date.valueOf(updateStartDate));
                updatePkg.setEndDate(java.sql.Date.valueOf(updateEndDate));
                updatePkg.setDuration(updateDuration);
                updatePkg.setDestinations(updateDestinations);
                updatePkg.setDescription(updateDescription);

                // Update package
                boolean updateSuccess = packageService.updatePackage(updatePkg);

                if (updateSuccess) {
                    response.sendRedirect(request.getContextPath() + "/tour-package-manager-packages?action=list&success=2");
                } else {
                    response.sendRedirect(request.getContextPath() + "/tour-package-manager-packages?action=edit&id=" + updateId + "&error=1");
                }
                break;

            case "delete":
                // Get package ID
                int deleteId = Integer.parseInt(request.getParameter("packageId"));

                // Delete package
                boolean deleteSuccess = packageService.deletePackage(deleteId);

                if (deleteSuccess) {
                    response.sendRedirect(request.getContextPath() + "/tour-package-manager-packages?action=list&success=3");
                } else {
                    response.sendRedirect(request.getContextPath() + "/tour-package-manager-packages?action=list&error=2");
                }
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/tour-package-manager-packages");
                break;
        }
    }
}