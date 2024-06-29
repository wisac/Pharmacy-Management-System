package com.pharmacy.ui;

import com.pharmacy.database.DatabaseConnection;
import com.pharmacy.model.Drug;
import com.pharmacy.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * The AddDrugForm class represents the UI form for adding a new drug to the pharmacy system.
 * It provides input fields for drug details and allows the selection of suppliers.
 */
public class AddDrugForm {

    /**
     * Displays the Add Drug form in a new window.
     * The form includes input fields for drug details and a dropdown for selecting suppliers.
     */
    public void showForm() {
        Stage stage = new Stage();
        stage.setTitle("Add Drug");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        // Drug Code input
        Label drugCodeLabel = new Label("Drug Code:");
        GridPane.setConstraints(drugCodeLabel, 0, 0);
        TextField drugCodeInput = new TextField();
        GridPane.setConstraints(drugCodeInput, 1, 0);

        // Drug Name input
        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);

        // Drug Description input
        Label descriptionLabel = new Label("Description:");
        GridPane.setConstraints(descriptionLabel, 0, 2);
        TextArea descriptionInput = new TextArea();
        descriptionInput.setPrefRowCount(3); // Set number of rows for TextArea
        GridPane.setConstraints(descriptionInput, 1, 2);

        // Drug Price input
        Label priceLabel = new Label("Price:");
        GridPane.setConstraints(priceLabel, 0, 3);
        TextField priceInput = new TextField();
        GridPane.setConstraints(priceInput, 1, 3);

        // Drug Quantity input
        Label quantityLabel = new Label("Quantity:");
        GridPane.setConstraints(quantityLabel, 0, 4);
        TextField quantityInput = new TextField();
        GridPane.setConstraints(quantityInput, 1, 4);

        // Suppliers dropdown
        Label suppliersLabel = new Label("Suppliers:");
        GridPane.setConstraints(suppliersLabel, 0, 5);
        ComboBox<Supplier> suppliersCombo = new ComboBox<>();
        suppliersCombo.setPrefWidth(200);
        GridPane.setConstraints(suppliersCombo, 1, 5);

        // Database connection to fetch suppliers
        DatabaseConnection db = new DatabaseConnection();
        List<Supplier> allSuppliers = db.getAllSuppliers();

        ObservableList<Supplier> suppliersList = FXCollections.observableArrayList(allSuppliers);
        suppliersCombo.setItems(suppliersList);

        // Add Drug button
        Button addButton = new Button("Add Drug");
        GridPane.setConstraints(addButton, 1, 6);

        addButton.setOnAction(e -> {
            String drugCode = drugCodeInput.getText();
            String name = nameInput.getText();
            String description = descriptionInput.getText();
            double price = Double.parseDouble(priceInput.getText());
            int quantity = Integer.parseInt(quantityInput.getText());

            // Create the Drug object
            Drug drug = new Drug(drugCode, name, description, price, quantity);

            // Add the drug to the database
            db.addDrug(drug);

            // Get the drug code after insertion
            drugCode = drug.getDrugCode(); // This ensures you have the correct drug code after insertion

            // Link selected suppliers to the drug
            Supplier selectedSupplier = suppliersCombo.getValue();
            if (selectedSupplier != null) {
                drug.addSupplier(selectedSupplier); // Add to the local Drug object
                db.linkSupplierToDrug(drugCode, selectedSupplier.getSupplierId()); // Persist in the database
            }

            stage.close();
        });

        gridPane.getChildren().addAll(drugCodeLabel, drugCodeInput, nameLabel, nameInput, descriptionLabel,
                descriptionInput, priceLabel, priceInput, quantityLabel, quantityInput, suppliersLabel,
                suppliersCombo, addButton);

        Scene scene = new Scene(gridPane, 600, 400); // Increased width and height
        stage.setScene(scene);
        stage.show();
    }
}
