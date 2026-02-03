package com.example.validation.dao;

import com.example.validation.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public static class Order {
        private int orderId;
        private int clientId;
        private Date orderDate;

        public Order(int orderId, int clientId, Date orderDate) {
            this.orderId = orderId;
            this.clientId = clientId;
            this.orderDate = orderDate;
        }

        public int getOrderId() { return orderId; }
        public int getClientId() { return clientId; }
        public Date getOrderDate() { return orderDate; }
    }

    public void addOrder(int orderId, int clientId, Date orderDate) {
        String sql = "INSERT INTO OrderTable (order_id, client_id, order_date) VALUES (?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, clientId);
            stmt.setDate(3, orderDate);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT order_id, client_id, order_date FROM OrderTable";
        try(Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                list.add(new Order(
                        rs.getInt("order_id"),
                        rs.getInt("client_id"),
                        rs.getDate("order_date")
                ));
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return list;
    }

    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM OrderTable WHERE order_id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }
}
