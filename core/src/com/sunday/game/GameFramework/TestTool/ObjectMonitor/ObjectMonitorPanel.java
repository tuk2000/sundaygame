package com.sunday.game.GameFramework.TestTool.ObjectMonitor;

import com.sunday.game.GameFramework.TestTool.ToolPanel;

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
