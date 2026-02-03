package com.example.validation.dao;

import com.example.validation.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    public static class Supplier {
        private int supplierId;
        private String region;
        private double salary;

        public Supplier(int supplierId, String region, double salary) {
            this.supplierId = supplierId;
            this.region = region;
            this.salary = salary;
        }

        public int getSupplierId() { return supplierId; }
        public String getRegion() { return region; }
        public double getSalary() { return salary; }
    }

    public void addSupplier(int supplierId, String region, double salary) {
        String sql = "INSERT INTO Supplier (supplier_id, region, salary) VALUES (?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplierId);
            stmt.setString(2, region);
            stmt.setDouble(3, salary);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> list = new ArrayList<>();
        String sql = "SELECT supplier_id, region, salary FROM Supplier";
        try(Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                list.add(new Supplier(
                        rs.getInt("supplier_id"),
                        rs.getString("region"),
                        rs.getDouble("salary")
                ));
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return list;
    }

    public void deleteSupplier(int supplierId) {
        String sql = "DELETE FROM Supplier WHERE supplier_id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplierId);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }
}
