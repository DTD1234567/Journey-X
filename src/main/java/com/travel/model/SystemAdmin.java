// File: src/main/java/com/travel/model/SystemAdmin.java
package com.travel.model;

public class SystemAdmin extends Employee {
    // Default constructor
    public SystemAdmin() {
        super();
    }

    // Parameterized constructor
    public SystemAdmin(int employeeId, String name, String jobTitle, String department,
                       String contactNumber, String email, String password) {
        super(employeeId, name, jobTitle, department, contactNumber, email, password);
    }
}