package com.pharmacy.ui;

import com.pharmacy.database.DatabaseConnection;
import com.pharmacy.model.Drug;
import com.pharmacy.model.PurchaseHistory;
import com.pharmacy.model.Supplier;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ViewDrugs {

    private TableView<Drug> table; // Make table a class-level variable

    public void showView() {
        Stage stage = new Stage();
        stage.setTitle("View Drugs");

        table = new TableView<>(); // Initialize table here

        ObservableList<Drug> drugs = FXCollections.observableArrayList(new DatabaseConnection().getAllDrugs());

        TableColumn<Drug, String> codeColumn = new TableColumn<>("Drug Code");
        codeColumn.setMinWidth(100);
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("drugCode"));

        TableColumn<Drug, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Drug, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMinWidth(300);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Drug, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Drug, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Drug, String> suppliersColumn = new TableColumn<>("Suppliers");
        suppliersColumn.setMinWidth(200);
        suppliersColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Drug, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Drug, String> data) {
                Drug drug = data.getValue();
                List<Supplier> suppliers = drug.getSuppliers();
                String supplierNames = suppliers.stream()
                        .map(Supplier::getName)
                        .collect(Collectors.joining(", "));
                return new SimpleStringProperty(supplierNames);
            }
        });

        // Delete column setup
        TableColumn<Drug, Void> deleteColumn = new TableColumn<>("Actions");
        deleteColumn.setMinWidth(100);
        deleteColumn.setSortable(false);

        Callback<TableColumn<Drug, Void>, TableCell<Drug, Void>> cellFactory = new Callback<TableColumn<Drug, Void>, TableCell<Drug, Void>>() {
            @Override
            public TableCell<Drug, Void> call(final TableColumn<Drug, Void> param) {
                final TableCell<Drug, Void> cell = new TableCell<Drug, Void>() {

                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction((event) -> {
                            Drug drug = getTableView().getItems().get(getIndex());
                            DatabaseConnection db = new DatabaseConnection();
                            db.deleteDrug(drug.getDrugCode()); // Delete drug
                            refreshTable(); // Refresh table after deletion
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
                return cell;
            }
        };

        deleteColumn.setCellFactory(cellFactory);

        TableColumn<Drug, Void> purchaseColumn = new TableColumn<>("Actions");
        purchaseColumn.setMinWidth(100);
        purchaseColumn.setSortable(false);

        Callback<TableColumn<Drug, Void>, TableCell<Drug, Void>> purchaseCellFactory = new Callback<TableColumn<Drug, Void>, TableCell<Drug, Void>>() {
            @Override
            public TableCell<Drug, Void> call(final TableColumn<Drug, Void> param) {
                final TableCell<Drug, Void> cell = new TableCell<Drug, Void>() {

                    private final Button purchaseButton = new Button("Purchase");

                    {
                        purchaseButton.setOnAction((event) -> {
                            Drug drug = getTableView().getItems().get(getIndex());
                            showPurchaseForm(drug.getDrugCode()); // Show purchase form for the selected drug
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(purchaseButton);
                        }
                    }
                };
                return cell;
            }
        };

        purchaseColumn.setCellFactory(purchaseCellFactory);

        table.setItems(drugs);
        table.getColumns().addAll(codeColumn, nameColumn, descriptionColumn, priceColumn, quantityColumn, suppliersColumn, deleteColumn, purchaseColumn);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    // Method to refresh table after deleting a drug
    private void refreshTable() {
        ObservableList<Drug> drugs = FXCollections.observableArrayList(new DatabaseConnection().getAllDrugs());
        table.setItems(drugs);
    }

    // Method to show the purchase form
    private void showPurchaseForm(String drugCode) {
        PurchaseDrugForm purchaseDrugForm = new PurchaseDrugForm();
        purchaseDrugForm.showForm(drugCode);
    }

    private void purchaseDrug(Drug drug, int quantity, String buyer) {
        double totalAmount = drug.getPrice() * quantity;
        PurchaseHistory purchaseHistory = new PurchaseHistory(0, drug.getDrugCode(), new Date(24,6,28), buyer, quantity, totalAmount);

        DatabaseConnection db = new DatabaseConnection();
        db.addPurchaseHistory(purchaseHistory);

        // Optionally, you might want to update the quantity of the drug in the database
         //db.updateDrugQuantity(drug.getDrugCode(), drug.getQuantity() - quantity);

        // Refresh the table or update UI as needed
        refreshTable(); // Make sure this method updates the UI with current data
    }

    // Other methods...

}
