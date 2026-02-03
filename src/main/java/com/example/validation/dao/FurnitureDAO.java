package com.example.validation.dao;

import com.example.validation.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FurnitureDAO {

    public static class Furniture {
        private int furnitureId;
        private String color;
        private String size;
        private String material;
        private String type;
        private double weight;
        private String name;

        public Furniture(int furnitureId, String color, String size, String material,
                         String type, double weight, String name) {
            this.furnitureId = furnitureId;
            this.color = color;
            this.size = size;
            this.material = material;
            this.type = type;
            this.weight = weight;
            this.name = name;
        }

        public int getFurnitureId() { return furnitureId; }
        public String getColor() { return color; }
        public String getSize() { return size; }
        public String getMaterial() { return material; }
        public String getType() { return type; }
        public double getWeight() { return weight; }
        public String getName() { return name; }
    }

    public void addFurniture(int furnitureId, String color, String size, String material,
                             String type, double weight, String name) {
        String sql = "INSERT INTO Furniture (furniture_id, color, furniture_size, material, furniture_type, weight, furniture_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, furnitureId);
            stmt.setString(2, color);
            stmt.setString(3, size);
            stmt.setString(4, material);
            stmt.setString(5, type);
            stmt.setDouble(6, weight);
            stmt.setString(7, name);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }

    public List<Furniture> getAllFurniture() {
        List<Furniture> list = new ArrayList<>();
        String sql = "SELECT furniture_id, color, furniture_size, material, furniture_type, weight, furniture_name FROM Furniture";
        try(Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                list.add(new Furniture(
                        rs.getInt("furniture_id"),
                        rs.getString("color"),
                        rs.getString("furniture_size"),
                        rs.getString("material"),
                        rs.getString("furniture_type"),
                        rs.getDouble("weight"),
                        rs.getString("furniture_name")
                ));
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return list;
    }

    public void deleteFurniture(int furnitureId) {
        String sql = "DELETE FROM Furniture WHERE furniture_id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, furnitureId);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }
}
