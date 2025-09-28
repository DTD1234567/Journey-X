// File: src/main/java/com/travel/model/Customer.java
package com.travel.model;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String customerName;
    private String nicNumber;
    private String gender;
    private String password;
    private String address;
    private String email;
    private String contactNumber;

    // Default constructor
    public Customer() {
    }

    // Parameterized constructor
    public Customer(int customerId, String firstName, String lastName, String customerName,
                    String nicNumber,String contactNumber, String gender, String password, String address, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerName = customerName;
        this.nicNumber = nicNumber;
        this.gender = gender;
        this.password = password;
        this.address = address;
        this.contactNumber=contactNumber;
        this.email = email;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNicNumber() {
        return nicNumber;
    }

    public void setNicNumber(String nicNumber) {
        this.nicNumber = nicNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", firstName=" + firstName +
                ", lastName=" + lastName + ", customerName=" + customerName +
                ", nicNumber=" + nicNumber + ", gender=" + gender +
                ", password=" + password + ", address=" + address +
                ", email=" + email + "]";
    }

}