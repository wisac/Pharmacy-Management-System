package com.pharmacy.ui;

import com.pharmacy.database.DatabaseConnection;
import com.pharmacy.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class SearchSupplierForm {
    public void showForm() {
        Stage stage = new Stage();
        stage.setTitle("Search Suppliers");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label searchLabel = new Label("Search Term:");
        GridPane.setConstraints(searchLabel, 0, 0);
        TextField searchInput = new TextField();
        GridPane.setConstraints(searchInput, 1, 0);

        Label searchByLabel = new Label("Search By:");
        GridPane.setConstraints(searchByLabel, 0, 1);
        ComboBox<String> searchByCombo = new ComboBox<>();
        searchByCombo.getItems().addAll("Name", "Location");
        searchByCombo.setValue("Name"); // Default selection
        GridPane.setConstraints(searchByCombo, 1, 1);

        Button searchButton = new Button("Search");
        GridPane.setConstraints(searchButton, 2, 0);

        TableView<Supplier> supplierTable = new TableView<>();
        TableColumn<Supplier, String> supplierIdCol = new TableColumn<>("Supplier ID");
        supplierIdCol.setCellValueFactory(cellData -> cellData.getValue().supplierIdProperty());

        TableColumn<Supplier, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Supplier, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

        supplierTable.getColumns().addAll(supplierIdCol, nameCol, locationCol);
        GridPane.setConstraints(supplierTable, 0, 2, 3, 1);

        DatabaseConnection db = new DatabaseConnection();

        searchButton.setOnAction(e -> {
            String searchTerm = searchInput.getText();
            String searchBy = searchByCombo.getValue().toLowerCase(); // Get selected search criteria
            List<Supplier> suppliers = db.searchSuppliers(searchTerm, searchBy);
            ObservableList<Supplier> supplierList = FXCollections.observableArrayList(suppliers);
            supplierTable.setItems(supplierList);
        });

        gridPane.getChildren().addAll(searchLabel, searchInput, searchByLabel, searchByCombo, searchButton, supplierTable);

        Scene scene = new Scene(gridPane, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
