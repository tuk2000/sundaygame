package com.sunday.game.GameFramework.TestTool;

import javax.swing.*;

public class ToolForm extends JFrame {
    private JTable defaultTable;
    private JPanel defaultPanel;
    private TestDataTableModel tableModel;


    public ToolForm(TestDataTableModel tableModel) {
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

    public void setTableModel(TestDataTableModel tableModel){
        this.tableModel = tableModel;
        defaultTable.setModel(tableModel);
    }

    public void updateTable(){
        defaultTable.updateUI();
        defaultPanel.updateUI();
    }

}
