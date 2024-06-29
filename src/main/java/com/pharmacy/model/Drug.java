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

    /**
     * Constructor to initialize a Drug object.
     *
     * @param drugCode    the code of the drug
     * @param name        the name of the drug
     * @param description the description of the drug
     * @param price       the price of the drug
     * @param quantity    the quantity of the drug
     */
    public Drug(String drugCode, String name, String description, double price, int quantity) {
        this.drugCode = new SimpleStringProperty(drugCode);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.suppliers = new ArrayList<>();
    }

    /**
     * Gets the drug code.
     *
     * @return the drug code
     */
    public String getDrugCode() {
        return drugCode.get();
    }

    /**
     * Sets the drug code.
     *
     * @param drugCode the drug code
     */
    public void setDrugCode(String drugCode) {
        this.drugCode.set(drugCode);
    }

    /**
     * Gets the drug code property.
     *
     * @return the drug code property
     */
    public StringProperty drugCodeProperty() {
        return drugCode;
    }

    /**
     * Gets the name of the drug.
     *
     * @return the name of the drug
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of the drug.
     *
     * @param name the name of the drug
     */
    public void setName(String name) {
        this.name.set(name);
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
     * Gets the description of the drug.
     *
     * @return the description of the drug
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Sets the description of the drug.
     *
     * @param description the description of the drug
     */
    public void setDescription(String description) {
        this.description.set(description);
    }

    /**
     * Gets the description property.
     *
     * @return the description property
     */
    public StringProperty descriptionProperty() {
        return description;
    }

    /**
     * Gets the price of the drug.
     *
     * @return the price of the drug
     */
    public double getPrice() {
        return price.get();
    }

    /**
     * Sets the price of the drug.
     *
     * @param price the price of the drug
     */
    public void setPrice(double price) {
        this.price.set(price);
    }

    /**
     * Gets the price property.
     *
     * @return the price property
     */
    public DoubleProperty priceProperty() {
        return price;
    }

    /**
     * Gets the quantity of the drug.
     *
     * @return the quantity of the drug
     */
    public int getQuantity() {
        return quantity.get();
    }

    /**
     * Sets the quantity of the drug.
     *
     * @param quantity the quantity of the drug
     */
    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    /**
     * Gets the quantity property.
     *
     * @return the quantity property
     */
    public IntegerProperty quantityProperty() {
        return quantity;
    }

    /**
     * Gets the list of suppliers for the drug.
     *
     * @return the list of suppliers
     */
    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    /**
     * Sets the list of suppliers for the drug.
     *
     * @param suppliers the list of suppliers
     */
    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    /**
     * Adds a supplier to the list of suppliers for the drug.
     *
     * @param supplier the supplier to add
     */
    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    /**
     * Removes a supplier from the list of suppliers for the drug.
     *
     * @param supplier the supplier to remove
     */
    public void removeSupplier(Supplier supplier) {
        suppliers.remove(supplier);
    }
}
