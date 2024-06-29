package com.pharmacy.ui;

import com.pharmacy.database.DatabaseConnection;
import com.pharmacy.model.Drug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

        table.setItems(drugs);
        table.getColumns().addAll(codeColumn, nameColumn, descriptionColumn, priceColumn, quantityColumn);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table);

        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
