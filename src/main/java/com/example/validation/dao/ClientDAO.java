package com.example.validation.dao;

import com.example.validation.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientDAO {


    public static class Client {
        private int clientId;
        private String name;
        private String email;
        private String phone;
        private String address;

        public Client(int clientId, String name, String email, String phone, String address) {
            this.clientId = clientId;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.address = address;
        }

        public int getClientId() { return clientId; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getAddress() { return address; }
    }

    public void addClient(int clientId, String name, String email, String phone, String address) {
        String sql = "INSERT INTO Client (client_id, name, email, phone, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clientId);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, address);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT client_id, name, email, phone, address FROM Client";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("client_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void updateClient(int clientId, String name, String email, String phone, String address) {
        String sql = "UPDATE Client SET name = ?, email = ?, phone = ?, address = ? WHERE client_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, address);
            stmt.setInt(5, clientId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int clientId) {
        String sql = "DELETE FROM Client WHERE client_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clientId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
