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

public class ViewPurchaseHistory {
    public void showForm() {
        Stage stage = new Stage();
        stage.setTitle("View Purchase History");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label searchLabel = new Label("Drug Code:");
        GridPane.setConstraints(searchLabel, 0, 0);
        TextField searchInput = new TextField();
        GridPane.setConstraints(searchInput, 1, 0);

        Button searchButton = new Button("Search");
        GridPane.setConstraints(searchButton, 1, 1);

        TableView<PurchaseHistory> table = new TableView<>();

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

        searchButton.setOnAction(e -> {
            String drugCode = searchInput.getText();
            DatabaseConnection db = new DatabaseConnection();
            ObservableList<PurchaseHistory> purchaseHistory = FXCollections.observableArrayList(db.getPurchaseHistory(drugCode));

            table.setItems(purchaseHistory);
            table.getColumns().setAll(idColumn, drugCodeColumn, purchaseDateColumn, buyerColumn, quantityColumn, totalAmountColumn);
        });

        gridPane.getChildren().addAll(searchLabel, searchInput, searchButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(gridPane, table);

        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
