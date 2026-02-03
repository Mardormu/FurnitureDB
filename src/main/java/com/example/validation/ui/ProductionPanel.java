package com.example.validation.ui;

import com.example.validation.dao.ProductionDAO;
import com.example.validation.dao.ProductionDAO.Production;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class ProductionPanel extends JPanel {

    private final ProductionDAO dao = new ProductionDAO();
    private JTextField txtId, txtFurnitureId, txtQuantity, txtStartDate, txtEndDate;
    private DefaultTableModel model;

    public ProductionPanel() {
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(5,2,5,5));
        txtId = new JTextField();
        txtFurnitureId = new JTextField();
        txtQuantity = new JTextField();
        txtStartDate = new JTextField();
        txtEndDate = new JTextField();

        form.add(new JLabel("Production ID:")); form.add(txtId);
        form.add(new JLabel("Furniture ID:")); form.add(txtFurnitureId);
        form.add(new JLabel("Quantity:")); form.add(txtQuantity);
        form.add(new JLabel("Start Date (yyyy-mm-dd):")); form.add(txtStartDate);
        form.add(new JLabel("End Date (yyyy-mm-dd):")); form.add(txtEndDate);

        add(form, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"ID","Furniture ID","Quantity","Start Date","End Date"},0
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
            dao.addProduction(
                    Integer.parseInt(txtId.getText()),
                    Integer.parseInt(txtFurnitureId.getText()),
                    Integer.parseInt(txtQuantity.getText()),
                    Date.valueOf(txtStartDate.getText()),
                    Date.valueOf(txtEndDate.getText())
            );
            refreshTable();
        });

        delete.addActionListener(e -> {
            dao.deleteProduction(Integer.parseInt(txtId.getText()));
            refreshTable();
        });

        refresh.addActionListener(e -> refreshTable());

        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Production> list = dao.getAllProduction();
        for(Production p : list) {
            model.addRow(new Object[]{
                    p.getProductionId(), p.getFurnitureId(), p.getQuantity(),
                    p.getStartDate(), p.getEndDate()
            });
        }
    }
}
