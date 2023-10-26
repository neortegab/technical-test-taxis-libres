package com.taxislibres.technicaltest.Models;


public class Bill {
    private long id;
    private double totalAmount;
    private String desc;
    private User user;

    public Bill(){}

    public Bill(double totalAmount, String desc, User user) {
        this.totalAmount = totalAmount;
        this.desc = desc;
        this.user = user;
    }

    public Bill(long id, double totalAmount, String desc, User user){
        this.id = id;
        this.totalAmount = totalAmount;
        this.desc = desc;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }
}