package com.travel.model;

public class CustomerPhone {
    private int customerId;
    private String phoneNumber;

    public int getCustomerId(){ return customerId; }
    public void setCustomerId(int id){ this.customerId = id; }
    public String getPhoneNumber(){ return phoneNumber; }
    public void setPhoneNumber(String s){ this.phoneNumber = s; }
}
