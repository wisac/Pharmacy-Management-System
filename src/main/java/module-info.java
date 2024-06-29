module com.pharmacy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.pharmacy.ui;
    exports com.pharmacy.model;
    exports com.pharmacy.database;
}
