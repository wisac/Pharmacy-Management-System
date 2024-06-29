package com.pharmacy.ui;

import com.pharmacy.database.DatabaseConnection;
import com.pharmacy.model.Drug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class SearchDrugForm {
    public void showForm() {
        Stage stage = new Stage();
        stage.setTitle("Search Drugs");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        // Search Term input
        Label searchLabel = new Label("Search Term:");
        GridPane.setConstraints(searchLabel, 0, 0);
        TextField searchInput = new TextField();
        GridPane.setConstraints(searchInput, 1, 0);

        // Search By selection
        Label searchByLabel = new Label("Search By:");
        GridPane.setConstraints(searchByLabel, 0, 1);
        ComboBox<String> searchByCombo = new ComboBox<>();
        searchByCombo.getItems().addAll("Drug Code", "Name", "Price");
        searchByCombo.setValue("Drug Code"); // Default selection
        GridPane.setConstraints(searchByCombo, 1, 1);

        // Search Button
        Button searchButton = new Button("Search");
        GridPane.setConstraints(searchButton, 2, 0);

        // Table to display search results
        TableView<Drug> drugTable = new TableView<>();
        TableColumn<Drug, String> drugCodeCol = new TableColumn<>("Drug Code");
        drugCodeCol.setCellValueFactory(cellData -> cellData.getValue().drugCodeProperty());

        TableColumn<Drug, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Drug, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        drugTable.getColumns().addAll(drugCodeCol, nameCol, priceCol);
        GridPane.setConstraints(drugTable, 0, 2, 3, 1);

        // Database connection
        DatabaseConnection db = new DatabaseConnection();

        // Search action
        searchButton.setOnAction(e -> {
            String searchTerm = searchInput.getText();
            String searchBy = searchByCombo.getValue().toLowerCase().replace(" ", "_"); // Convert to database field name
            List<Drug> drugs = db.searchDrugs(searchTerm, searchBy);
            ObservableList<Drug> drugList = FXCollections.observableArrayList(drugs);
            drugTable.setItems(drugList);
        });

        gridPane.getChildren().addAll(searchLabel, searchInput, searchByLabel, searchByCombo, searchButton, drugTable);

        Scene scene = new Scene(gridPane, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
