package com.travel.model;

import java.util.Date;

public class Booking {
    private int bookingId;
    private Date bookingDate;
    private String confirmationStatus;
    private int customerId;
    private int packageId;
    private int numTravelers;
    private double totalPrice;

    public Booking() {
    }

    public Booking(int bookingId, double totalPrice,Date bookingDate, String confirmationStatus, int customerId, int packageId, int numTravelers) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.confirmationStatus = confirmationStatus;
        this.customerId = customerId;
        this.packageId = packageId;
        this.numTravelers = numTravelers;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    public String getConfirmationStatus() { return confirmationStatus; }
    public void setConfirmationStatus(String confirmationStatus) { this.confirmationStatus = confirmationStatus; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getPackageId() { return packageId; }
    public void setPackageId(int packageId) { this.packageId = packageId; }
    public int getNumTravelers() { return numTravelers; }
    public void setNumTravelers(int numTravelers) { this.numTravelers = numTravelers; }
}