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

public class ViewSuppliers {
    public void showView() {
        Stage stage = new Stage();
        stage.setTitle("View Suppliers");

        TableView<Supplier> table = new TableView<>();
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

        table.setItems(suppliers);
        table.getColumns().addAll(idColumn, nameColumn, locationColumn);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table);

        Scene scene = new Scene(vbox, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
