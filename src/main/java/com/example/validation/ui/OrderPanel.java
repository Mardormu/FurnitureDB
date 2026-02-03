package com.example.validation.ui;

import com.example.validation.dao.OrderDAO;
import com.example.validation.dao.OrderDAO.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class OrderPanel extends JPanel {

    private final OrderDAO dao = new OrderDAO();
    private JTextField txtId, txtClientId, txtDate;
    private DefaultTableModel model;

    public OrderPanel() {
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(3,2,5,5));
        txtId = new JTextField();
        txtClientId = new JTextField();
        txtDate = new JTextField(); // yyyy-mm-dd

        form.add(new JLabel("Order ID:")); form.add(txtId);
        form.add(new JLabel("Client ID:")); form.add(txtClientId);
        form.add(new JLabel("Date (yyyy-mm-dd):")); form.add(txtDate);

        add(form, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"Order ID","Client ID","Date"},0
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
            dao.addOrder(
                    Integer.parseInt(txtId.getText()),
                    Integer.parseInt(txtClientId.getText()),
                    Date.valueOf(txtDate.getText())
            );
            refreshTable();
        });

        delete.addActionListener(e -> {
            dao.deleteOrder(Integer.parseInt(txtId.getText()));
            refreshTable();
        });

        refresh.addActionListener(e -> refreshTable());

        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Order> list = dao.getAllOrders();
        for(Order o : list) {
            model.addRow(new Object[]{o.getOrderId(), o.getClientId(), o.getOrderDate()});
        }
    }
}
