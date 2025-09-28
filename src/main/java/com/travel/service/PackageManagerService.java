// File: src/main/java/com/travel/service/PackageManagerService.java
package com.travel.service;

import com.travel.dao.PackageManagerDAO;
import com.travel.model.PackageManager;
import java.util.List;

public class PackageManagerService {
    private PackageManagerDAO packageManagerDAO;

    public PackageManagerService() {
        packageManagerDAO = new PackageManagerDAO();
    }

    // Authenticate package manager login
    public PackageManager authenticatePackageManager(String email, String password) {
        return packageManagerDAO.validatePackageManager(email, password);
    }

    // Get package manager by ID
    public PackageManager getPackageManagerById(int employeeId) {
        return packageManagerDAO.getPackageManagerById(employeeId);
    }

    // Get package manager by email
    public PackageManager getPackageManagerByEmail(String email) {
        return packageManagerDAO.getPackageManagerByEmail(email);
    }

    // Add new package manager
    public boolean addPackageManager(PackageManager manager) {
        // Check if email already exists
        PackageManager existingManager = packageManagerDAO.getPackageManagerByEmail(manager.getEmail());
        if (existingManager != null) {
            return false; // Email already exists
        }

        return packageManagerDAO.addPackageManager(manager);
    }

    // Update package manager
    public boolean updatePackageManager(PackageManager manager) {
        return packageManagerDAO.updatePackageManager(manager);
    }

    // Delete package manager
    public boolean deletePackageManager(int employeeId) {
        return packageManagerDAO.deletePackageManager(employeeId);
    }

    // Get all package managers
    public List<PackageManager> getAllPackageManagers() {
        return packageManagerDAO.getAllPackageManagers();
    }
}