package com.sunday.tool.ObjectMonitor;

import com.sunday.tool.ToolPanel;

import javax.swing.*;
import java.util.HashMap;

public class ObjectMonitorPanel extends ToolPanel {
    private JTable defaultTable;
    private JPanel panel;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        defaultTable = new JTable(new ObjectTableModel(new HashMap<>()));
    }

    public void updateView() {
        SwingUtilities.invokeLater(defaultTable::updateUI);
    }

    @Override
    public void useContentData(Object contentData) {
        if (contentData instanceof ObjectTableModel) {
            defaultTable.setModel((ObjectTableModel) contentData);
        }
        updateView();
    }

}
