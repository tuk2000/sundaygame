package com.sunday.game.GameFramework.TestTool.ObjectMonitor;

import javax.swing.*;
import java.util.HashMap;

public class ObjectMonitorPanel extends JComponent {
    private JTable defaultTable;
    private JPanel panel;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        defaultTable = new JTable(new ObjectTableModel(new HashMap<>()));
    }

    public void setTableModel(ObjectTableModel tableModel) {
        SwingUtilities.invokeLater(() -> {
            defaultTable.setModel(tableModel);
            defaultTable.revalidate();
        });
    }

}
