package com.example.validation.dao;

import com.example.validation.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO {

    public static class Warehouse {
        private int warehouseId;
        private String location;
        private int capacity;

        public Warehouse(int warehouseId, String location, int capacity) {
            this.warehouseId = warehouseId;
            this.location = location;
            this.capacity = capacity;
        }

        public int getWarehouseId() { return warehouseId; }
        public String getLocation() { return location; }
        public int getCapacity() { return capacity; }
    }

    public void addWarehouse(int warehouseId, String location, int capacity) {
        String sql = "INSERT INTO Warehouse (warehouse_id, location, capacity) VALUES (?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, warehouseId);
            stmt.setString(2, location);
            stmt.setInt(3, capacity);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }

    public List<Warehouse> getAllWarehouses() {
        List<Warehouse> list = new ArrayList<>();
        String sql = "SELECT warehouse_id, location, capacity FROM Warehouse";
        try(Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                list.add(new Warehouse(
                        rs.getInt("warehouse_id"),
                        rs.getString("location"),
                        rs.getInt("capacity")
                ));
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return list;
    }

    public void deleteWarehouse(int warehouseId) {
        String sql = "DELETE FROM Warehouse WHERE warehouse_id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, warehouseId);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }
}
