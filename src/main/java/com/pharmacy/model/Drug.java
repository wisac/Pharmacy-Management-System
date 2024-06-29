package com.pharmacy.model;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class Drug {
    private final StringProperty drugCode;
    private final StringProperty name;
    private final StringProperty description;
    private final DoubleProperty price;
    private final IntegerProperty quantity;
    private List<Supplier> suppliers;

    public Drug(String drugCode, String name, String description, double price, int quantity) {
        this.drugCode = new SimpleStringProperty(drugCode);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.suppliers = new ArrayList<>();
    }

    // Getters and setters with properties

    public String getDrugCode() {
        return drugCode.get();
    }

    public void setDrugCode(String drugCode) {
        this.drugCode.set(drugCode);
    }

    public StringProperty drugCodeProperty() {
        return drugCode;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    public void removeSupplier(Supplier supplier) {
        suppliers.remove(supplier);
    }
}
