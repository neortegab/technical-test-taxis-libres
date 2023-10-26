package com.taxislibres.technicaltest.Models;

import jakarta.persistence.*;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private double totalAmount;
    private String description;
    @ManyToOne
    private User user;

    public Bill(){}

    public Bill(double totalAmount, String description, User user) {
        this.totalAmount = totalAmount;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}