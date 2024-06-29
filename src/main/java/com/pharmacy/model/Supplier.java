package com.pharmacy.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Supplier class represents a supplier in the pharmacy system.
 * It includes properties for supplier ID, name, and location, which are
 * implemented using JavaFX properties to support data binding in the UI.
 */
public class Supplier {
    private final StringProperty supplierId;
    private final StringProperty name;
    private final StringProperty location;

    /**
     * Constructs a Supplier with the specified ID, name, and location.
     *
     * @param supplierId the ID of the supplier
     * @param name       the name of the supplier
     * @param location   the location of the supplier
     */
    public Supplier(String supplierId, String name, String location) {
        this.supplierId = new SimpleStringProperty(supplierId);
        this.name = new SimpleStringProperty(name);
        this.location = new SimpleStringProperty(location);
    }

    /**
     * Gets the supplier ID.
     *
     * @return the supplier ID
     */
    public String getSupplierId() {
        return supplierId.get();
    }

    /**
     * Gets the supplier ID property.
     *
     * @return the supplier ID property
     */
    public StringProperty supplierIdProperty() {
        return supplierId;
    }

    /**
     * Sets the supplier ID.
     *
     * @param supplierId the new supplier ID
     */
    public void setSupplierId(String supplierId) {
        this.supplierId.set(supplierId);
    }

    /**
     * Gets the name of the supplier.
     *
     * @return the name of the supplier
     */
    public String getName() {
        return name.get();
    }

    /**
     * Gets the name property.
     *
     * @return the name property
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Sets the name of the supplier.
     *
     * @param name the new name of the supplier
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Gets the location of the supplier.
     *
     * @return the location of the supplier
     */
    public String getLocation() {
        return location.get();
    }

    /**
     * Gets the location property.
     *
     * @return the location property
     */
    public StringProperty locationProperty() {
        return location;
    }

    /**
     * Sets the location of the supplier.
     *
     * @param location the new location of the supplier
     */
    public void setLocation(String location) {
        this.location.set(location);
    }

    /**
     * Returns the string representation of the supplier.
     * The name of the supplier is returned as the string representation.
     *
     * @return the name of the supplier
     */
    @Override
    public String toString() {
        return name.getValue(); // Return the name of the supplier
    }
}
