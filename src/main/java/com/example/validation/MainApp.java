package com.example.validation;

import com.example.validation.ui.*;

import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {

    public MainApp() {
        setTitle("FurnitureDB");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Clients", new ClientPanel());
        tabs.addTab("Users", new UserPanel());
        tabs.addTab("Suppliers", new SupplierPanel());
        tabs.addTab("Furniture", new FurniturePanel());
        tabs.addTab("Delivery", new DeliveryPanel());
        tabs.addTab("Orders", new OrderPanel());
        tabs.addTab("Products", new ProductPanel());
        tabs.addTab("Warehouse", new WarehousePanel());
        tabs.addTab("Payments", new PaymentPanel());
        tabs.addTab("Production", new ProductionPanel());

        add(tabs, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }
}
