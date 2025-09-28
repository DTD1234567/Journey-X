// File: src/main/java/com/travel/model/Package.java
package com.travel.model;

import java.sql.Date;
import java.util.List;

public class Package {
    private int packageId;
    private String packageName;
    private double price;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private String duration;
    private String destinations;
    private String description;
    private List<String> images;  // store image paths


    // Default constructor
    public Package() {
    }

    // Parameterized constructor


    public Package(int packageId, String packageName, double price, Date startDate, Date endDate, String duration, String destinations, String description, List<String> images) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.destinations = destinations;
        this.description = description;
        this.images = images;
    }

    // Getters and Setters
    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDestinations() {
        return destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}