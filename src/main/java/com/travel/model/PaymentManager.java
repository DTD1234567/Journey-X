// File: src/main/java/com/travel/model/PaymentManager.java
package com.travel.model;

public class PaymentManager extends Employee {
    // Default constructor
    public PaymentManager() {
        super();
    }

    // Parameterized constructor
    public PaymentManager(int employeeId, String name, String jobTitle, String department,
                          String contactNumber, String email, String password) {
        super(employeeId, name, jobTitle, department, contactNumber, email, password);
    }
}