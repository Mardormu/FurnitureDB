package com.example.validation.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.example.validation.dao.ClientDAO;


public class ClientPanel extends JPanel {

    private final ClientDAO clientDAO = new ClientDAO();

    private JTextField txtId, txtName, txtEmail, txtPhone, txtAddress;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh;
    private JTable table;
    private DefaultTableModel tableModel;

    public ClientPanel() {
        setLayout(new BorderLayout(10, 10));

        // --- Form Panel ---
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        formPanel.add(new JLabel("Client ID:"));
        txtId = new JTextField(); formPanel.add(txtId);

        formPanel.add(new JLabel("Name:"));
        txtName = new JTextField(); formPanel.add(txtName);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField(); formPanel.add(txtEmail);

        formPanel.add(new JLabel("Phone:"));
        txtPhone = new JTextField(); formPanel.add(txtPhone);

        formPanel.add(new JLabel("Address:"));
        txtAddress = new JTextField(); formPanel.add(txtAddress);



        add(formPanel, BorderLayout.NORTH);

        // --- Table ---
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Name", "Email", "Phone", "Address"}, 0
        );
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtId.setText(tableModel.getValueAt(row, 0).toString());
                    txtName.setText(tableModel.getValueAt(row, 1).toString());
                    txtEmail.setText(tableModel.getValueAt(row, 2).toString());
                    txtPhone.setText(tableModel.getValueAt(row, 3).toString());
                    txtAddress.setText(tableModel.getValueAt(row, 4).toString());
                }
            }
        });

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Add"); buttonPanel.add(btnAdd);
        btnUpdate = new JButton("Update"); buttonPanel.add(btnUpdate);
        btnDelete = new JButton("Delete"); buttonPanel.add(btnDelete);
        btnRefresh = new JButton("Refresh"); buttonPanel.add(btnRefresh);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Button Actions ---
        btnAdd.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                clientDAO.addClient(id, txtName.getText(), txtEmail.getText(),
                        txtPhone.getText(), txtAddress.getText());
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Client ID must be a number");
            }
        });

        btnUpdate.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                clientDAO.updateClient(id, txtName.getText(), txtEmail.getText(),
                        txtPhone.getText(), txtAddress.getText());
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Client ID must be a number");
            }
        });

        btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                clientDAO.deleteClient(id);
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Client ID must be a number");
            }
        });

        btnRefresh.addActionListener(e -> refreshTable());

        // Initial table load
        refreshTable();
    }

    /**
     * Refreshes the table to display all clients from the database.
     */
    private void refreshTable() {
        tableModel.setRowCount(0);
        List<ClientDAO.Client> clients = clientDAO.getAllClients();

        for (ClientDAO.Client c : clients) {
            tableModel.addRow(new Object[]{
                    c.getClientId(),
                    c.getName(),
                    c.getEmail(),
                    c.getPhone(),
                    c.getAddress(),

            });
        }
    }
}
