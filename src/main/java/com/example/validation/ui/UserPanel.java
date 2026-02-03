package com.example.validation.ui;

import com.example.validation.dao.UserDAO;
import com.example.validation.dao.UserDAO.SystemUser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserPanel extends JPanel {

    private final UserDAO dao = new UserDAO();
    private JTextField txtId, txtName, txtEmail, txtPhone;
    private DefaultTableModel model;

    public UserPanel() {
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(4,2,5,5));
        txtId = new JTextField();
        txtName = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();

        form.add(new JLabel("User ID:")); form.add(txtId);
        form.add(new JLabel("Name:")); form.add(txtName);
        form.add(new JLabel("Email:")); form.add(txtEmail);
        form.add(new JLabel("Phone:")); form.add(txtPhone);

        add(form, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"ID","Name","Email","Phone"},0
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
            dao.addUser(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText()
            );
            refreshTable();
        });

        delete.addActionListener(e -> {
            dao.deleteUser(Integer.parseInt(txtId.getText()));
            refreshTable();
        });

        refresh.addActionListener(e -> refreshTable());

        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<SystemUser> list = dao.getAllUsers();
        for(SystemUser u : list) {
            model.addRow(new Object[]{u.getUserId(), u.getName(), u.getEmail(), u.getPhone()});
        }
    }
}
