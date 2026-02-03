package com.example.validation.ui;

import com.example.validation.dao.ProductDAO;
import com.example.validation.dao.ProductDAO.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductPanel extends JPanel {

    private final ProductDAO dao = new ProductDAO();
    private JTextField txtId, txtPrice, txtQuantity;
    private DefaultTableModel model;

    public ProductPanel() {
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(3,2,5,5));
        txtId = new JTextField();
        txtPrice = new JTextField();
        txtQuantity = new JTextField();

        form.add(new JLabel("Product ID:")); form.add(txtId);
        form.add(new JLabel("Price:")); form.add(txtPrice);
        form.add(new JLabel("Quantity:")); form.add(txtQuantity);

        add(form, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"ID","Price","Quantity"},0
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
            dao.addProduct(
                    Integer.parseInt(txtId.getText()),
                    Double.parseDouble(txtPrice.getText()),
                    Integer.parseInt(txtQuantity.getText())
            );
            refreshTable();
        });

        delete.addActionListener(e -> {
            dao.deleteProduct(Integer.parseInt(txtId.getText()));
            refreshTable();
        });

        refresh.addActionListener(e -> refreshTable());

        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Product> list = dao.getAllProducts();
        for(Product p : list) {
            model.addRow(new Object[]{p.getProductId(), p.getPrice(), p.getQuantity()});
        }
    }
}
