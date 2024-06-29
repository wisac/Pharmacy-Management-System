package com.pharmacy.model;

import java.sql.Timestamp;
import java.util.Date;

public class PurchaseHistory {
    private int id;
    private String drugCode;
    private Timestamp purchaseDate;
    private String buyer;
    private int quantity;
    private double totalAmount;
    /**
     * Constructor to initialize a PurchaseHistory object.
     *
     * @param id            the ID of the purchase
     * @param drugCode      the code of the drug
     * @param purchaseDate  the date of the purchase
     * @param buyer         the buyer of the drug
     * @param quantity      the quantity purchased
     * @param totalAmount   the total amount spent
     */
    public PurchaseHistory(int id, String drugCode, Timestamp purchaseDate, String buyer, int quantity, double totalAmount) {
        this.id = id;
        this.drugCode = drugCode;
        this.purchaseDate = purchaseDate;
        this.buyer = buyer;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    /**
     * Gets the ID of the purchase.
     *
     * @return the ID of the purchase
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the purchase.
     *
     * @param id the ID of the purchase
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the drug code of the purchase.
     *
     * @return the drug code of the purchase
     */
    public String getDrugCode() {
        return drugCode;
    }

    /**
     * Sets the drug code of the purchase.
     *
     * @param drugCode the drug code of the purchase
     */
    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    /**
     * Gets the date of the purchase.
     *
     * @return the date of the purchase
     */
    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the date of the purchase.
     *
     * @param purchaseDate the date of the purchase
     */
    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * Gets the buyer of the purchase.
     *
     * @return the buyer of the purchase
     */
    public String getBuyer() {
        return buyer;
    }

    /**
     * Sets the buyer of the purchase.
     *
     * @param buyer the buyer of the purchase
     */
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    /**
     * Gets the quantity purchased.
     *
     * @return the quantity purchased
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity purchased.
     *
     * @param quantity the quantity purchased
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the total amount spent on the purchase.
     *
     * @return the total amount spent on the purchase
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the total amount spent on the purchase.
     *
     * @param totalAmount the total amount spent on the purchase
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
