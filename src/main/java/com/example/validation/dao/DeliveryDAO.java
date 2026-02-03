package com.example.validation.dao;

import com.example.validation.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAO {

    // ===== DTO за JOIN =====
    public static class DeliveryDetails {
        private int deliveryId;
        private String address;
        private Date date;

        private String clientName;
        private String clientEmail;

        private String supplierRegion;
        private double supplierSalary;

        public DeliveryDetails(int deliveryId, String address, Date date,
                               String clientName, String clientEmail,
                               String supplierRegion, double supplierSalary) {
            this.deliveryId = deliveryId;
            this.address = address;
            this.date = date;
            this.clientName = clientName;
            this.clientEmail = clientEmail;
            this.supplierRegion = supplierRegion;
            this.supplierSalary = supplierSalary;
        }

        public int getDeliveryId() { return deliveryId; }
        public String getAddress() { return address; }
        public Date getDate() { return date; }
        public String getClientName() { return clientName; }
        public String getClientEmail() { return clientEmail; }
        public String getSupplierRegion() { return supplierRegion; }
        public double getSupplierSalary() { return supplierSalary; }
    }

    // ===== JOIN заявка =====
    public List<DeliveryDetails> getDeliveryDetails() {
        List<DeliveryDetails> list = new ArrayList<>();

        String sql =
                "SELECT d.delivery_id, d.delivery_address, d.delivery_date, " +
                        "c.name AS client_name, c.email AS client_email, " +
                        "s.region AS supplier_region, s.salary AS supplier_salary " +
                        "FROM Delivery d " +
                        "JOIN Client c ON d.client_id = c.client_id " +
                        "JOIN Supplier s ON d.supplier_id = s.supplier_id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new DeliveryDetails(
                        rs.getInt("delivery_id"),
                        rs.getString("delivery_address"),
                        rs.getDate("delivery_date"),
                        rs.getString("client_name"),
                        rs.getString("client_email"),
                        rs.getString("supplier_region"),
                        rs.getDouble("supplier_salary")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
