package com.sunday.game.GameFramework.TestTool.Logger;

import javax.swing.*;
import java.util.ArrayList;

public class LogPanel extends JComponent {
    private JList logList;
    private JPanel panel;

    public void UpdateList(ListModel listModel) {
        SwingUtilities.invokeLater(() -> {
            logList.setModel(listModel);
            logList.validate();
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        logList = new JList(new LogListModel(new ArrayList<LogMessage>()));
    }
}
