package com.example.validation.ui;

import com.example.validation.dao.FurnitureDAO;
import com.example.validation.dao.FurnitureDAO.Furniture;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FurniturePanel extends JPanel {

    private final FurnitureDAO dao = new FurnitureDAO();
    private JTextField txtId, txtColor, txtSize, txtMaterial, txtType, txtWeight, txtName;
    private DefaultTableModel model;

    public FurniturePanel() {
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(7,2,5,5));
        txtId = new JTextField();
        txtColor = new JTextField();
        txtSize = new JTextField();
        txtMaterial = new JTextField();
        txtType = new JTextField();
        txtWeight = new JTextField();
        txtName = new JTextField();

        form.add(new JLabel("Furniture ID:")); form.add(txtId);
        form.add(new JLabel("Color:")); form.add(txtColor);
        form.add(new JLabel("Size:")); form.add(txtSize);
        form.add(new JLabel("Material:")); form.add(txtMaterial);
        form.add(new JLabel("Type:")); form.add(txtType);
        form.add(new JLabel("Weight:")); form.add(txtWeight);
        form.add(new JLabel("Name:")); form.add(txtName);

        add(form, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"ID","Color","Size","Material","Type","Weight","Name"},0
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
            dao.addFurniture(
                    Integer.parseInt(txtId.getText()),
                    txtColor.getText(),
                    txtSize.getText(),
                    txtMaterial.getText(),
                    txtType.getText(),
                    Double.parseDouble(txtWeight.getText()),
                    txtName.getText()
            );
            refreshTable();
        });

        delete.addActionListener(e -> {
            dao.deleteFurniture(Integer.parseInt(txtId.getText()));
            refreshTable();
        });

        refresh.addActionListener(e -> refreshTable());

        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Furniture> list = dao.getAllFurniture();
        for(Furniture f : list) {
            model.addRow(new Object[]{
                    f.getFurnitureId(), f.getColor(), f.getSize(), f.getMaterial(),
                    f.getType(), f.getWeight(), f.getName()
            });
        }
    }
}
