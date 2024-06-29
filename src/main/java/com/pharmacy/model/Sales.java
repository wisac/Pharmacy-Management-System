package com.pharmacy.model;

import java.util.Date;

public class Sales {
    private int id;
    private String drugCode;
    private Date saleDate;
    private int quantity;

    /**
     * Constructor to initialize a Sales object.
     *
     * @param id        the ID of the sale
     * @param drugCode  the code of the drug sold
     * @param saleDate  the date of the sale
     * @param quantity  the quantity sold
     */
    public Sales(int id, String drugCode, Date saleDate, int quantity) {
        this.id = id;
        this.drugCode = drugCode;
        this.saleDate = saleDate;
        this.quantity = quantity;
    }

    /**
     * Gets the ID of the sale.
     *
     * @return the ID of the sale
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the sale.
     *
     * @param id the ID of the sale
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the drug code of the sale.
     *
     * @return the drug code of the sale
     */
    public String getDrugCode() {
        return drugCode;
    }

    /**
     * Sets the drug code of the sale.
     *
     * @param drugCode the drug code of the sale
     */
    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    /**
     * Gets the date of the sale.
     *
     * @return the date of the sale
     */
    public Date getSaleDate() {
        return saleDate;
    }

    /**
     * Sets the date of the sale.
     *
     * @param saleDate the date of the sale
     */
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    /**
     * Gets the quantity sold.
     *
     * @return the quantity sold
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity sold.
     *
     * @param quantity the quantity sold
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
