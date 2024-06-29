package com.pharmacy.database;

import com.pharmacy.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private final String url = "jdbc:mysql://localhost:3306/pharmacy";
    private final String user = "root";
    private final String password = "password";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void addDrug(Drug drug) {
        String sql = "INSERT INTO drugs(drug_code, name, description, price, quantity) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, drug.getDrugCode());
            pstmt.setString(2, drug.getName());
            pstmt.setString(3, drug.getDescription());
            pstmt.setDouble(4, drug.getPrice());
            pstmt.setInt(5, drug.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Drug getDrug(String drugCode) {
        String sql = "SELECT * FROM drugs WHERE drug_code = ?";
        Drug drug = null;

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, drugCode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                drug = new Drug(
                        rs.getString("drug_code"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drug;
    }

    public List<Drug> getAllDrugs() {
        List<Drug> drugs = new ArrayList<>();
        String sql = "SELECT * FROM drugs";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Drug drug = new Drug(
                        rs.getString("drug_code"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                drugs.add(drug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drugs;
    }




        public List<Drug> searchDrugs(String searchTerm, String searchBy) {
            List<Drug> drugs = new ArrayList<>();
            String sql = "SELECT * FROM drugs WHERE ";

            switch (searchBy) {
                case "name":
                    sql += "name LIKE ?";
                    break;
                case "price":
                    sql += "price = ?";
                    break;
                case "drug_code":
                    sql += "drug_code LIKE ?";
                    break;
                default:
                    return drugs;
            }

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (searchBy.equals("price")) {
                    pstmt.setDouble(1, Double.parseDouble(searchTerm));
                } else {
                    pstmt.setString(1, "%" + searchTerm + "%");
                }
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Drug drug = new Drug(
                            rs.getString("drug_code"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("quantity")
                    );
                    drugs.add(drug);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return drugs;
        }







    public List<PurchaseHistory> getPurchaseHistory(String drugCode) {
        List<PurchaseHistory> purchaseHistoryList = new ArrayList<>();
        String sql = "SELECT * FROM purchase_history WHERE drug_code = ? ORDER BY purchase_date DESC";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, drugCode);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PurchaseHistory purchaseHistory = new PurchaseHistory(
                        rs.getInt("id"),
                        rs.getString("drug_code"),
                        rs.getTimestamp("purchase_date"),
                        rs.getString("buyer"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_amount")
                );
                purchaseHistoryList.add(purchaseHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchaseHistoryList;
    }

    public List<Supplier> getSuppliers(String drugCode) {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT s.* FROM suppliers s JOIN drug_suppliers ds ON s.supplier_id = ds.supplier_id WHERE ds.drug_code = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, drugCode);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getString("supplier_id"),
                        rs.getString("name"),
                        rs.getString("location")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    // Add these methods to DatabaseConnection.java

    public void addSupplier(Supplier supplier) {
        String sql = "INSERT INTO suppliers(supplier_id, name, location) VALUES(?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, supplier.getSupplierId());
            pstmt.setString(2, supplier.getName());
            pstmt.setString(3, supplier.getLocation());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getString("supplier_id"),
                        rs.getString("name"),
                        rs.getString("location")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    public void linkSupplierToDrug(String drugCode, String supplierId) {
        String sql = "INSERT INTO drug_suppliers(drug_code, supplier_id) VALUES (?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, drugCode);
            pstmt.setString(2, supplierId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Supplier> searchSuppliers(String searchTerm, String searchBy) {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers WHERE ";

        switch (searchBy) {
            case "name":
                sql += "name LIKE ?";
                break;
            case "location":
                sql += "location LIKE ?";
                break;
            default:
                return suppliers;
        }

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + searchTerm + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getString("supplier_id"),
                        rs.getString("name"),
                        rs.getString("location")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }


}
