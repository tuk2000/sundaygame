package com.sunday.game.GameFramework.TestTool;

import com.sunday.game.GameFramework.TestTool.Logger.GameLogger;
import com.sunday.game.GameFramework.TestTool.Logger.LogPanel;
import com.sunday.game.GameFramework.TestTool.ObjectMonitor.ObjectMonitor;
import com.sunday.game.GameFramework.TestTool.ObjectMonitor.ObjectMonitorPanel;
import com.sunday.game.GameFramework.TestTool.ScreenLoader.ScreenLoader;
import com.sunday.game.GameFramework.TestTool.ScreenLoader.ScreenLoaderPanel;

import javax.swing.*;

public class TestTool extends JFrame {
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private LogPanel logPanel;
    private ObjectMonitorPanel objectMonitorPanel;
    private ScreenLoaderPanel screenLoaderPanel;

    public TestTool() {
        setTitle("TestTool");
        setContentPane(panel);
        setLocation(0, 0);
        setSize(1000, 800);
        setVisible(true);
        pack();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        logPanel = new LogPanel();
        GameLogger.getInstance().setContentPanel(logPanel);
        objectMonitorPanel = new ObjectMonitorPanel();
        ObjectMonitor.getInstance().setContentPanel(objectMonitorPanel);
        screenLoaderPanel=new ScreenLoaderPanel();
        ScreenLoader.getInstance().setContentPanel(screenLoaderPanel);
    }

    public void switchOnOrOff() {
        SwingUtilities.invokeLater(() -> {
            setVisible(!isVisible());
            System.gc();
        });
    }

}
