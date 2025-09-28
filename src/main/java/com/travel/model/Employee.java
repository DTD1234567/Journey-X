// File: src/main/java/com/travel/model/Employee.java
package com.travel.model;

public class Employee {
    protected int employeeId;
    protected String name;
    protected String jobTitle;
    protected String department;
    protected String contactNumber;
    protected String email;
    protected String password;

    // Default constructor
    public Employee() {
    }

    // Parameterized constructor
    public Employee(int employeeId, String name, String jobTitle, String department,
                    String contactNumber, String email, String password) {
        this.employeeId = employeeId;
        this.name = name;
        this.jobTitle = jobTitle;
        this.department = department;
        this.contactNumber = contactNumber;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}