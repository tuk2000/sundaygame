package com.sunday.game.GameFramework.TestTool.ObjectMonitor;

import javax.swing.*;

public class ObjectMonitorForm extends JFrame {
    private JTable defaultTable;
    private JPanel defaultPanel;
    private ObjectTableModel tableModel;


    public ObjectMonitorForm(ObjectTableModel tableModel) {
        this.tableModel = tableModel;
        setSize(400, 800);
        setVisible(true);
        setContentPane(defaultPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        defaultTable = new JTable(tableModel);
    }

    public void setTableModel(ObjectTableModel tableModel) {
        this.tableModel = tableModel;
        SwingUtilities.invokeLater(() -> {
            defaultTable.setModel(tableModel);
            defaultTable.revalidate();
        });
    }

}
