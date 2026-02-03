package com.example.validation.ui;

import com.example.validation.dao.SupplierDAO;
import com.example.validation.dao.SupplierDAO.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SupplierPanel extends JPanel {
    private final SupplierDAO dao = new SupplierDAO();
    private JTextField txtId, txtRegion, txtSalary;
    private DefaultTableModel model;
    private JTable table;

    public SupplierPanel() {
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(3,2,5,5));
        txtId = new JTextField(); txtRegion = new JTextField(); txtSalary = new JTextField();
        form.add(new JLabel("Supplier ID:")); form.add(txtId);
        form.add(new JLabel("Region:")); form.add(txtRegion);
        form.add(new JLabel("Salary:")); form.add(txtSalary);
        add(form, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID","Region","Salary"},0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");
        JButton refresh = new JButton("Refresh");
        buttons.add(add); buttons.add(delete); buttons.add(refresh);
        add(buttons, BorderLayout.SOUTH);

        add.addActionListener(e -> {
            dao.addSupplier(
                    Integer.parseInt(txtId.getText()),
                    txtRegion.getText(),
                    Double.parseDouble(txtSalary.getText())
            );
            refreshTable();
        });

        delete.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if(selected != -1){
                int id = (int) model.getValueAt(selected,0);
                dao.deleteSupplier(id);
                refreshTable();
            } else JOptionPane.showMessageDialog(this,"Select a row to delete!");
        });

        refresh.addActionListener(e -> refreshTable());
        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Supplier> list = dao.getAllSuppliers();
        for(Supplier s : list){
            model.addRow(new Object[]{s.getSupplierId(), s.getRegion(), s.getSalary()});
        }
    }
}
