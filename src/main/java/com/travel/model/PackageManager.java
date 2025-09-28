package com.travel.model;

// Model class for PackageManager entity (extends Employee)
public class PackageManager extends Employee {

    // Default constructor
    public PackageManager() {
        super();
    }

    // Parameterized constructor
    public PackageManager(int employeeId, String name, String jobTitle,
                          String department, String contactNumber,
                          String email, String password) {
        super(employeeId, name, jobTitle, department, contactNumber, email, password);
    }

    // All other methods are inherited from Employee class
}