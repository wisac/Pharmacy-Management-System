package com.pharmacy.database;

import com.pharmacy.model.Drug;
import com.pharmacy.model.PurchaseHistory;
import com.pharmacy.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private final String url = "jdbc:mysql://localhost:3306/pharmacy";
    private final String user = "root";
    private final String password = "password";

    /**
     * Establishes a connection to the database.
     *
     * @return the Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Adds a new drug to the database.
     *
     * @param drug the Drug object to be added
     */
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

    /**
     * Retrieves a drug from the database based on the drug code.
     *
     * @param drugCode the code of the drug to be retrieved
     * @return the Drug object if found, otherwise null
     */
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

    /**
     * Retrieves all drugs from the database.
     *
     * @return a list of Drug objects
     */
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

                // Fetch suppliers for this drug
                List<Supplier> suppliers = getSuppliersForDrug(drug.getDrugCode());
                drug.setSuppliers(suppliers);

                drugs.add(drug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drugs;
    }

    /**
     * Deletes a drug from the database based on the drug code.
     *
     * @param drugCode the code of the drug to delete
     */
    public void deleteDrug(String drugCode) {
        String sql = "DELETE FROM drugs WHERE drug_code = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, drugCode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for drugs in the database based on a search term and category.
     *
     * @param searchTerm the term to search for
     * @param searchBy   the category to search by (name, price, drug_code)
     * @return a list of Drug objects matching the search criteria
     */
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

    /**
     * Retrieves the purchase history for a specific drug.
     *
     * @param drugCode the code of the drug
     * @return a list of PurchaseHistory objects
     */
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

    /**
     * Retrieves the suppliers for a specific drug.
     *
     * @param drugCode the code of the drug
     * @return a list of Supplier objects
     */
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

    /**
     * Adds a new supplier to the database.
     *
     * @param supplier the Supplier object to be added
     */
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

    /**
     * Retrieves all suppliers from the database.
     *
     * @return a list of Supplier objects
     */
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

    /**
     * Links a supplier to a drug in the database.
     *
     * @param drugCode   the code of the drug
     * @param supplierId the ID of the supplier
     */
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

    /**
     * Searches for suppliers in the database based on a search term and category.
     *
     * @param searchTerm the term to search for
     * @param searchBy   the category to search by (name, location)
     * @return a list of Supplier objects matching the search criteria
     */
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

    /**
     * Retrieves the suppliers for a specific drug.
     *
     * @param drugCode the code of the drug
     * @return a list of Supplier objects
     */
    public List<Supplier> getSuppliersForDrug(String drugCode) {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT s.supplier_id, s.name, s.location FROM suppliers s " +
                "JOIN drug_suppliers ds ON s.supplier_id = ds.supplier_id " +
                "WHERE ds.drug_code = ?";

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


    /**
     * Deletes a supplier from the database based on the supplier ID.
     *
     * @param supplierId the ID of the supplier to delete
     */
    public void deleteSupplier(String supplierId) {
        // Delete associated records in drug_suppliers first
        String deleteDrugSuppliersSql = "DELETE FROM drug_suppliers WHERE supplier_id = ?";
        String deleteSupplierSql = "DELETE FROM suppliers WHERE supplier_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmtDeleteDrugSuppliers = conn.prepareStatement(deleteDrugSuppliersSql);
             PreparedStatement pstmtDeleteSupplier = conn.prepareStatement(deleteSupplierSql)) {

            // Delete from drug_suppliers table
            pstmtDeleteDrugSuppliers.setString(1, supplierId);
            pstmtDeleteDrugSuppliers.executeUpdate();

            // Delete from suppliers table
            pstmtDeleteSupplier.setString(1, supplierId);
            pstmtDeleteSupplier.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
