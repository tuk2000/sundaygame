package com.sunday.game.GameFramework.TestTool.Logger;

import com.sunday.game.GameFramework.TestTool.ToolPanel;

import javax.swing.*;
import java.util.ArrayList;

public class LogPanel extends ToolPanel {
    private JList logList;
    private JPanel panel;

    @Override
    public void updateView() {
        SwingUtilities.invokeLater(logList::updateUI);
    }

    @Override
    public void useContentData(Object contentData) {
        if (contentData instanceof ListModel) {
            logList.setModel((ListModel) contentData);
        }
        updateView();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        logList = new JList(new LogListModel(new ArrayList<LogMessage>()));
    }
}
