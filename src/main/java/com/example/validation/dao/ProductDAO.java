package com.example.validation.dao;

import com.example.validation.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public static class Product {
        private int productId;
        private double price;
        private int quantity;

        public Product(int productId, double price, int quantity) {
            this.productId = productId;
            this.price = price;
            this.quantity = quantity;
        }

        public int getProductId() { return productId; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }
    }

    public void addProduct(int productId, double price, int quantity) {
        String sql = "INSERT INTO Product (product_id, price, quantity) VALUES (?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT product_id, price, quantity FROM Product";
        try(Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                list.add(new Product(
                        rs.getInt("product_id"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                ));
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return list;
    }

    public void deleteProduct(int productId) {
        String sql = "DELETE FROM Product WHERE product_id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }
}
