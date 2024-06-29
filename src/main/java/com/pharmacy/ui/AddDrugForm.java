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

import java.util.ArrayList;
import java.util.List;

public class AddDrugForm {
    public void showForm() {
        Stage stage = new Stage();
        stage.setTitle("Add Drug");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label drugCodeLabel = new Label("Drug Code:");
        GridPane.setConstraints(drugCodeLabel, 0, 0);
        TextField drugCodeInput = new TextField();
        GridPane.setConstraints(drugCodeInput, 1, 0);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);

        Label descriptionLabel = new Label("Description:");
        GridPane.setConstraints(descriptionLabel, 0, 2);
        TextArea descriptionInput = new TextArea();
        GridPane.setConstraints(descriptionInput, 1, 2);

        Label priceLabel = new Label("Price:");
        GridPane.setConstraints(priceLabel, 0, 3);
        TextField priceInput = new TextField();
        GridPane.setConstraints(priceInput, 1, 3);

        Label quantityLabel = new Label("Quantity:");
        GridPane.setConstraints(quantityLabel, 0, 4);
        TextField quantityInput = new TextField();
        GridPane.setConstraints(quantityInput, 1, 4);

        Label suppliersLabel = new Label("Suppliers:");
        GridPane.setConstraints(suppliersLabel, 0, 5);
        ComboBox<Supplier> suppliersCombo = new ComboBox<>();
        suppliersCombo.setPrefWidth(200);
        GridPane.setConstraints(suppliersCombo, 1, 5);

        DatabaseConnection db = new DatabaseConnection();
        List<Supplier> allSuppliers = db.getAllSuppliers();

        ObservableList<Supplier> suppliersList = FXCollections.observableArrayList(allSuppliers);
        suppliersCombo.setItems(suppliersList);

        Button addButton = new Button("Add Drug");
        GridPane.setConstraints(addButton, 1, 6);

        addButton.setOnAction(e -> {
            String drugCode = drugCodeInput.getText();
            String name = nameInput.getText();
            String description = descriptionInput.getText();
            double price = Double.parseDouble(priceInput.getText());
            int quantity = Integer.parseInt(quantityInput.getText());

            Drug drug = new Drug(drugCode, name, description, price, quantity);
            Supplier selectedSupplier = suppliersCombo.getValue();
            if (selectedSupplier != null) {
                drug.addSupplier(selectedSupplier);
                db.linkSupplierToDrug(drugCode, selectedSupplier.getSupplierId());
            }

            db.addDrug(drug);
            stage.close();
        });

        gridPane.getChildren().addAll(drugCodeLabel, drugCodeInput, nameLabel, nameInput, descriptionLabel,
                descriptionInput, priceLabel, priceInput, quantityLabel, quantityInput, suppliersLabel,
                suppliersCombo, addButton);

        Scene scene = new Scene(gridPane, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}
