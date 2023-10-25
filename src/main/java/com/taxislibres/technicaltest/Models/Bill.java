package com.taxislibres.technicaltest.Models;
public class Bill {
    private long id;
    private double totalAmount;
    private String desc;
    private long userId;

    public Bill(){}

    public Bill(double totalAmount, String desc, long userId) {
        this.totalAmount = totalAmount;
        this.desc = desc;
        this.userId = userId;
    }

    public Bill(long id, double totalAmount, String desc, long userId){
        this.id = id;
        this.totalAmount = totalAmount;
        this.desc = desc;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}