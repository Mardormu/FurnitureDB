package com.example.validation.dao;

import com.example.validation.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductionDAO {

    public static class Production {
        private int productionId;
        private int furnitureId;
        private int quantity;
        private Date startDate;
        private Date endDate;

        public Production(int productionId, int furnitureId, int quantity, Date startDate, Date endDate) {
            this.productionId = productionId;
            this.furnitureId = furnitureId;
            this.quantity = quantity;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public int getProductionId() { return productionId; }
        public int getFurnitureId() { return furnitureId; }
        public int getQuantity() { return quantity; }
        public Date getStartDate() { return startDate; }
        public Date getEndDate() { return endDate; }
    }

    public void addProduction(int productionId, int furnitureId, int quantity, Date startDate, Date endDate) {
        String sql = "INSERT INTO Production (production_id, furniture_id, quantity, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productionId);
            stmt.setInt(2, furnitureId);
            stmt.setInt(3, quantity);
            stmt.setDate(4, startDate);
            stmt.setDate(5, endDate);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }

    public List<Production> getAllProduction() {
        List<Production> list = new ArrayList<>();
        String sql = "SELECT production_id, furniture_id, quantity, start_date, end_date FROM Production";
        try(Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                list.add(new Production(
                        rs.getInt("production_id"),
                        rs.getInt("furniture_id"),
                        rs.getInt("quantity"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                ));
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return list;
    }

    public void deleteProduction(int productionId) {
        String sql = "DELETE FROM Production WHERE production_id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productionId);
            stmt.executeUpdate();
        } catch(SQLException e){ e.printStackTrace(); }
    }
}
