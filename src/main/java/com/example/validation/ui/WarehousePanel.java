package com.example.validation.ui;

import com.example.validation.dao.WarehouseDAO;
import com.example.validation.dao.WarehouseDAO.Warehouse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class WarehousePanel extends JPanel {

    private final WarehouseDAO dao = new WarehouseDAO();
    private JTextField txtId, txtLocation, txtCapacity;
    private DefaultTableModel model;

    public WarehousePanel() {
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(3,2,5,5));
        txtId = new JTextField();
        txtLocation = new JTextField();
        txtCapacity = new JTextField();

        form.add(new JLabel("Warehouse ID:")); form.add(txtId);
        form.add(new JLabel("Location:")); form.add(txtLocation);
        form.add(new JLabel("Capacity:")); form.add(txtCapacity);

        add(form, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"ID","Location","Capacity"},0
        );
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");
        JButton refresh = new JButton("Refresh");

        JPanel buttons = new JPanel();
        buttons.add(add); buttons.add(delete); buttons.add(refresh);
        add(buttons, BorderLayout.SOUTH);

        add.addActionListener(e -> {
            dao.addWarehouse(
                    Integer.parseInt(txtId.getText()),
                    txtLocation.getText(),
                    Integer.parseInt(txtCapacity.getText())
            );
            refreshTable();
        });

        delete.addActionListener(e -> {
            dao.deleteWarehouse(Integer.parseInt(txtId.getText()));
            refreshTable();
        });

        refresh.addActionListener(e -> refreshTable());

        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Warehouse> list = dao.getAllWarehouses();
        for(Warehouse w : list) {
            model.addRow(new Object[]{w.getWarehouseId(), w.getLocation(), w.getCapacity()});
        }
    }
}
