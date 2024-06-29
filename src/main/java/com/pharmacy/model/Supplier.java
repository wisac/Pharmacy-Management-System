package com.pharmacy.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Supplier {
    private final StringProperty supplierId;
    private final StringProperty name;
    private final StringProperty location;

    public Supplier(String supplierId, String name, String location) {
        this.supplierId = new SimpleStringProperty(supplierId);
        this.name = new SimpleStringProperty(name);
        this.location = new SimpleStringProperty(location);
    }

    public String getSupplierId() {
        return supplierId.get();
    }

    public StringProperty supplierIdProperty() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId.set(supplierId);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    @Override
    public String toString() {
        return name.getValue(); // Return the name of the supplier
    }
}
