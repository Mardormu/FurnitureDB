package com.example.validation.dao;

import com.example.validation.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public static class Payment {
        private int paymentId;
        private int orderId;
        private String bank;
        private String method;

        public Payment(int paymentId, int orderId, String bank, String method) {
            this.paymentId = paymentId;
            this.orderId = orderId;
            this.bank = bank;
            this.method = method;
        }

        public int getPaymentId() { return paymentId; }
        public int getOrderId() { return orderId; }
        public String getBank() { return bank; }
        public String getMethod() { return method; }
    }

    public void addPayment(int paymentId, int orderId, String bank, String method) {
        String sql = "INSERT INTO Payment (payment_id, order_id, bank, method) VALUES (?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paymentId);
            stmt.setInt(2, orderId);
            stmt.setString(3, bank);
            stmt.setString(4, method);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }

    public List<Payment> getAllPayments() {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT payment_id, order_id, bank, method FROM Payment";
        try(Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                list.add(new Payment(
                        rs.getInt("payment_id"),
                        rs.getInt("order_id"),
                        rs.getString("bank"),
                        rs.getString("method")
                ));
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return list;
    }

    public void deletePayment(int paymentId) {
        String sql = "DELETE FROM Payment WHERE payment_id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paymentId);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }
}
