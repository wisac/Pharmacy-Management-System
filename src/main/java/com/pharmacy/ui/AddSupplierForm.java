package com.pharmacy.ui;

import com.pharmacy.database.DatabaseConnection;
import com.pharmacy.model.Supplier;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The AddSupplierForm class represents the UI form for adding a new supplier to the pharmacy system.
 * It provides input fields for supplier details.
 */
public class AddSupplierForm {

    /**
     * Displays the Add Supplier form in a new window.
     * The form includes input fields for supplier ID, name, and location.
     */
    public void showForm() {
        Stage stage = new Stage();
        stage.setTitle("Add Supplier");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        // Supplier ID input
        Label supplierIdLabel = new Label("Supplier ID:");
        GridPane.setConstraints(supplierIdLabel, 0, 0);
        TextField supplierIdInput = new TextField();
        GridPane.setConstraints(supplierIdInput, 1, 0);

        // Supplier Name input
        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);

        // Supplier Location input
        Label locationLabel = new Label("Location:");
        GridPane.setConstraints(locationLabel, 0, 2);
        TextField locationInput = new TextField();
        GridPane.setConstraints(locationInput, 1, 2);

        // Add Supplier button
        Button addButton = new Button("Add Supplier");
        GridPane.setConstraints(addButton, 1, 3);

        addButton.setOnAction(e -> {
            String supplierId = supplierIdInput.getText();
            String name = nameInput.getText();
            String location = locationInput.getText();

            Supplier supplier = new Supplier(supplierId, name, location);
            DatabaseConnection db = new DatabaseConnection();
            db.addSupplier(supplier);

            stage.close();
        });

        gridPane.getChildren().addAll(supplierIdLabel, supplierIdInput, nameLabel, nameInput, locationLabel, locationInput, addButton);

        Scene scene = new Scene(gridPane, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
