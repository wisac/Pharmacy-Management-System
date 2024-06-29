package com.pharmacy.ui;

import com.pharmacy.database.DatabaseConnection;
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

import java.util.List;
import java.util.stream.Collectors;

public class ViewSuppliers {

    private TableView<Supplier> table; // Make table a class-level variable

    public void showView() {
        Stage stage = new Stage();
        stage.setTitle("View Suppliers");

        table = new TableView<>(); // Initialize table here

        ObservableList<Supplier> suppliers = FXCollections.observableArrayList(new DatabaseConnection().getAllSuppliers());

        TableColumn<Supplier, String> idColumn = new TableColumn<>("Supplier ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        TableColumn<Supplier, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Supplier, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setMinWidth(200);
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        // Delete column setup
        TableColumn<Supplier, Void> deleteColumn = new TableColumn<>("Actions");
        deleteColumn.setMinWidth(100);
        deleteColumn.setSortable(false);

        Callback<TableColumn<Supplier, Void>, TableCell<Supplier, Void>> cellFactory = new Callback<TableColumn<Supplier, Void>, TableCell<Supplier, Void>>() {
            @Override
            public TableCell<Supplier, Void> call(final TableColumn<Supplier, Void> param) {
                final TableCell<Supplier, Void> cell = new TableCell<Supplier, Void>() {

                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction((event) -> {
                            Supplier supplier = getTableView().getItems().get(getIndex());
                            DatabaseConnection db = new DatabaseConnection();
                            db.deleteSupplier(supplier.getSupplierId()); // Delete supplier
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

        table.setItems(suppliers);
        table.getColumns().addAll(idColumn, nameColumn, locationColumn, deleteColumn);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    // Method to refresh table after deleting a supplier
    private void refreshTable() {
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList(new DatabaseConnection().getAllSuppliers());
        table.setItems(suppliers);
    }
}
