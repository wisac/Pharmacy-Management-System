package com.pharmacy.ui;

import com.pharmacy.database.DatabaseConnection;
import com.pharmacy.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class represents a view to display a list of suppliers in a TableView.
 * Suppliers are fetched from the database and displayed with columns for ID, Name, and Location.
 */
public class ViewSuppliers {

    /**
     * Displays a TableView with a list of suppliers fetched from the database.
     * The TableView includes columns for Supplier ID, Name, and Location.
     */
    public void showView() {
        Stage stage = new Stage();
        stage.setTitle("View Suppliers");

        // TableView to display suppliers
        TableView<Supplier> table = new TableView<>();
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList(new DatabaseConnection().getAllSuppliers());

        // TableColumn for Supplier ID
        TableColumn<Supplier, String> idColumn = new TableColumn<>("Supplier ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        // TableColumn for Supplier Name
        TableColumn<Supplier, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // TableColumn for Supplier Location
        TableColumn<Supplier, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setMinWidth(200);
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        // Set items and columns to TableView
        table.setItems(suppliers);
        table.getColumns().addAll(idColumn, nameColumn, locationColumn);

        // VBox to hold TableView
        VBox vbox = new VBox();
        vbox.getChildren().addAll(table);

        // Create Scene and display stage
        Scene scene = new Scene(vbox, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
