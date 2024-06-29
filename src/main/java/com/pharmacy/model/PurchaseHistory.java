package com.pharmacy.model;

import java.util.Date;

public class PurchaseHistory {
    private int id;
    private String drugCode;
    private Date purchaseDate;
    private String buyer;
    private int quantity;
    private double totalAmount;

    public PurchaseHistory(int id, String drugCode, Date purchaseDate, String buyer, int quantity, double totalAmount) {
        this.id = id;
        this.drugCode = drugCode;
        this.purchaseDate = purchaseDate;
        this.buyer = buyer;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
