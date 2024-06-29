package com.pharmacy.ui;

import com.pharmacy.database.DatabaseConnection;
import com.pharmacy.model.PurchaseHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class represents a form to view purchase history of drugs in a Pharmacy Management System.
 * It allows searching by drug code and displays results in a TableView.
 */
public class ViewPurchaseHistory {

    /**
     * Displays a form to search and view purchase history of drugs.
     * Initializes a TableView to display purchase history records based on the search criteria.
     */
    public void showForm() {
        Stage stage = new Stage();
        stage.setTitle("View Purchase History");

        // GridPane for search input
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        // Label and TextField for drug code search
        Label searchLabel = new Label("Drug Code:");
        GridPane.setConstraints(searchLabel, 0, 0);
        TextField searchInput = new TextField();
        GridPane.setConstraints(searchInput, 1, 0);

        // Search button to initiate search
        Button searchButton = new Button("Search");
        GridPane.setConstraints(searchButton, 1, 1);

        // TableView to display purchase history records
        TableView<PurchaseHistory> table = new TableView<>();

        // Define columns for TableView
        TableColumn<PurchaseHistory, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<PurchaseHistory, String> drugCodeColumn = new TableColumn<>("Drug Code");
        drugCodeColumn.setMinWidth(100);
        drugCodeColumn.setCellValueFactory(new PropertyValueFactory<>("drugCode"));

        TableColumn<PurchaseHistory, String> purchaseDateColumn = new TableColumn<>("Purchase Date");
        purchaseDateColumn.setMinWidth(200);
        purchaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));

        TableColumn<PurchaseHistory, String> buyerColumn = new TableColumn<>("Buyer");
        buyerColumn.setMinWidth(200);
        buyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyer"));

        TableColumn<PurchaseHistory, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<PurchaseHistory, Double> totalAmountColumn = new TableColumn<>("Total Amount");
        totalAmountColumn.setMinWidth(100);
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        // Set action for search button to fetch and display purchase history records
        searchButton.setOnAction(e -> {
            String drugCode = searchInput.getText();
            DatabaseConnection db = new DatabaseConnection();
            ObservableList<PurchaseHistory> purchaseHistory = FXCollections.observableArrayList(db.getPurchaseHistory(drugCode));

            table.setItems(purchaseHistory);
            table.getColumns().setAll(idColumn, drugCodeColumn, purchaseDateColumn, buyerColumn, quantityColumn, totalAmountColumn);
        });

        // Add components to GridPane and TableView to VBox
        gridPane.getChildren().addAll(searchLabel, searchInput, searchButton);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(gridPane, table);

        // Create Scene and display stage
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
