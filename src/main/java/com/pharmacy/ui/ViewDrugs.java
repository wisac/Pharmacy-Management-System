package com.pharmacy.ui;

import com.pharmacy.database.DatabaseConnection;
import com.pharmacy.model.Drug;
import com.pharmacy.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;

// Existing imports...

public class ViewDrugs {
    public void showView() {
        Stage stage = new Stage();
        stage.setTitle("View Drugs");

        TableView<Drug> table = new TableView<>();
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
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");
                return new SimpleStringProperty(supplierNames);
            }
        });

        table.setItems(drugs);
        table.getColumns().addAll(codeColumn, nameColumn, descriptionColumn, priceColumn, quantityColumn, suppliersColumn);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table);

        Scene scene = new Scene(vbox, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
