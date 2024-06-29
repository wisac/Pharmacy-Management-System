package com.pharmacy.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The main class for the Pharmacy Management System application.
 * Initializes and displays the main user interface with menus and functionality.
 */
public class PharmacyManagementSystem extends Application {

    /**
     * Starts the application by setting up the primary stage and displaying the main UI.
     *
     * @param primaryStage The primary stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy Management System");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Load background image
        Image backgroundImage = new Image(getClass().getResource("/image2.jpg").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true); // Preserve aspect ratio
        backgroundImageView.fitWidthProperty().bind(scene.widthProperty()); // Bind width to scene width
        backgroundImageView.fitHeightProperty().bind(scene.heightProperty());
        backgroundImageView.setOpacity(0.5); // Adjust the transparency

        // Create a welcome message
        Label welcomeMessage = new Label("Welcome to Pharmacy Management System");
        welcomeMessage.setFont(new Font("Arial", 30));
        welcomeMessage.setStyle("-fx-text-fill: #2a2a2a; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 4, 0, 0, 2);"); // Set text color and add drop shadow

        // Create the "Browse All Drugs" button
        Button browseDrugsButton = new Button("Browse All Drugs");
        browseDrugsButton.setFont(new Font("Arial", 16));
        browseDrugsButton.setStyle("-fx-background-color: #2a2a2a; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        browseDrugsButton.setOnAction(e -> {
            ViewDrugs viewDrugsView = new ViewDrugs();
            viewDrugsView.showView();
        });

        // Center the welcome message and button
        VBox vBox = new VBox(20); // 20 is the spacing between elements
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(welcomeMessage, browseDrugsButton);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, vBox);

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
        menuBar.setStyle("-fx-font-size: large");
        historyMenu.getItems().addAll(viewPurchaseHistory);

        // Supplier Menu
        Menu supplierMenu = new Menu("Supplier");
        MenuItem addSupplier = new MenuItem("Add Supplier");
        MenuItem viewSuppliers = new MenuItem("View Suppliers");
        MenuItem searchSupplier = new MenuItem("Search Supplier");
        supplierMenu.getItems().addAll(addSupplier, viewSuppliers, searchSupplier);

        // Add Menus to MenuBar
        menuBar.getMenus().addAll(drugMenu, supplierMenu, historyMenu);

        // Set actions for menu items
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
        root.setCenter(stackPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method to launch the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
