package com.pharmacy.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PharmacyManagementSystem extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy Management System");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        MenuBar menuBar = new MenuBar();

        // Drug Menu
        Menu drugMenu = new Menu("Drug");
        MenuItem addDrug = new MenuItem("Add Drug");
        MenuItem viewDrugs = new MenuItem("View Drugs");
        MenuItem searchDrug = new MenuItem("Search Drug");
        drugMenu.getItems().addAll(addDrug, viewDrugs, searchDrug);

        // History Menu
        Menu historyMenu = new Menu("History");
        MenuItem viewPurchaseHistory = new MenuItem("View Purchase History");
        historyMenu.getItems().addAll(viewPurchaseHistory);

        // Supplier Menu
        Menu supplierMenu = new Menu("Supplier");
        MenuItem addSupplier = new MenuItem("Add Supplier");
        MenuItem viewSuppliers = new MenuItem("View Suppliers");
        MenuItem searchSupplier = new MenuItem("Search Supplier"); // New menu item
        supplierMenu.getItems().addAll(addSupplier, viewSuppliers, searchSupplier); // Add searchSupplier item

        // Add Menus to MenuBar
        menuBar.getMenus().addAll(drugMenu, supplierMenu, historyMenu);

        addDrug.setOnAction(e -> {
            AddDrugForm addDrugForm = new AddDrugForm();
            addDrugForm.showForm();
        });

        viewDrugs.setOnAction(e -> {
            ViewDrugs viewDrugsView = new ViewDrugs();
            viewDrugsView.showView();
        });

        searchDrug.setOnAction(e -> {
            SearchDrugForm searchDrugForm = new SearchDrugForm();
            searchDrugForm.showForm();
        });

        viewPurchaseHistory.setOnAction(e -> {
            ViewPurchaseHistory viewPurchaseHistoryView = new ViewPurchaseHistory();
            viewPurchaseHistoryView.showForm();
        });

        addSupplier.setOnAction(e -> {
            AddSupplierForm addSupplierForm = new AddSupplierForm();
            addSupplierForm.showForm();
        });

        viewSuppliers.setOnAction(e -> {
            ViewSuppliers viewSuppliersView = new ViewSuppliers();
            viewSuppliersView.showView();
        });

        searchSupplier.setOnAction(e -> {
            SearchSupplierForm searchSupplierForm = new SearchSupplierForm();
            searchSupplierForm.showForm();
        });

        root.setTop(menuBar);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
