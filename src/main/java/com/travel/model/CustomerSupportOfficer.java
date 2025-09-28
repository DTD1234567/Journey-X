// File: src/main/java/com/travel/model/CustomerSupportOfficer.java
package com.travel.model;

public class CustomerSupportOfficer extends Employee {
    // Default constructor
    public CustomerSupportOfficer() {
        super();
    }

    // Parameterized constructor
    public CustomerSupportOfficer(int employeeId, String name, String jobTitle, String department,
                                  String contactNumber, String email, String password) {
        super(employeeId, name, jobTitle, department, contactNumber, email, password);
    }
}