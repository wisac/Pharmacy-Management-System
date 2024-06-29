package com.pharmacy.model;

import java.util.Date;

public class Sales {
    private int id;
    private String drugCode;
    private Date saleDate;
    private int quantity;

    public Sales(int id, String drugCode, Date saleDate, int quantity) {
        this.id = id;
        this.drugCode = drugCode;
        this.saleDate = saleDate;
        this.quantity = quantity;
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

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
