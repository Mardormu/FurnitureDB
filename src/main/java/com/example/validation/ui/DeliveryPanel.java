package com.example.validation.ui;

import com.example.validation.dao.DeliveryDAO;
import com.example.validation.dao.DeliveryDAO.DeliveryDetails;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DeliveryPanel extends JPanel {

    private final DeliveryDAO dao = new DeliveryDAO();
    private DefaultTableModel model;

    public DeliveryPanel() {

        setLayout(new BorderLayout(10,10));

        model = new DefaultTableModel(
                new String[]{
                        "Delivery ID",
                        "Address",
                        "Date",
                        "Client Name",
                        "Client Email",
                        "Supplier Region",
                        "Supplier Salary"
                }, 0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> loadData());

        JPanel bottom = new JPanel();
        bottom.add(refresh);
        add(bottom, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        model.setRowCount(0);

        List<DeliveryDetails> list = dao.getDeliveryDetails();
        for (DeliveryDetails d : list) {
            model.addRow(new Object[]{
                    d.getDeliveryId(),
                    d.getAddress(),
                    d.getDate(),
                    d.getClientName(),
                    d.getClientEmail(),
                    d.getSupplierRegion(),
                    d.getSupplierSalary()
            });
        }
    }
}
