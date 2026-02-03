package com.example.validation.ui;

import com.example.validation.dao.PaymentDAO;
import com.example.validation.dao.PaymentDAO.Payment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PaymentPanel extends JPanel {

    private final PaymentDAO dao = new PaymentDAO();
    private JTextField txtId, txtOrderId, txtBank, txtMethod;
    private DefaultTableModel model;

    public PaymentPanel() {
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(4,2,5,5));
        txtId = new JTextField();
        txtOrderId = new JTextField();
        txtBank = new JTextField();
        txtMethod = new JTextField();

        form.add(new JLabel("Payment ID:")); form.add(txtId);
        form.add(new JLabel("Order ID:")); form.add(txtOrderId);
        form.add(new JLabel("Bank:")); form.add(txtBank);
        form.add(new JLabel("Method:")); form.add(txtMethod);

        add(form, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"Payment ID","Order ID","Bank","Method"},0
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
            dao.addPayment(
                    Integer.parseInt(txtId.getText()),
                    Integer.parseInt(txtOrderId.getText()),
                    txtBank.getText(),
                    txtMethod.getText()
            );
            refreshTable();
        });

        delete.addActionListener(e -> {
            dao.deletePayment(Integer.parseInt(txtId.getText()));
            refreshTable();
        });

        refresh.addActionListener(e -> refreshTable());

        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Payment> list = dao.getAllPayments();
        for(Payment p : list) {
            model.addRow(new Object[]{p.getPaymentId(), p.getOrderId(), p.getBank(), p.getMethod()});
        }
    }
}
