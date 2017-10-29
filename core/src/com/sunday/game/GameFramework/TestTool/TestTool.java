package com.sunday.game.GameFramework.TestTool;

import com.badlogic.gdx.Gdx;
import com.sunday.game.GameFramework.GameFramework;
import com.sunday.game.GameFramework.TestTool.Logger.GameLogger;
import com.sunday.game.GameFramework.TestTool.Logger.LogPanel;
import com.sunday.game.GameFramework.TestTool.ObjectMonitor.ObjectMonitor;
import com.sunday.game.GameFramework.TestTool.ObjectMonitor.ObjectMonitorPanel;
import com.sunday.game.GameFramework.TestTool.ScreenLoader.ScreenLoader;
import com.sunday.game.GameFramework.TestTool.ScreenLoader.ScreenLoaderPanel;

import javax.swing.*;

public class TestTool extends JFrame {
    private JPanel panel;
    private JTabbedPane tabbedPanel;
    private LogPanel logPanel;
    private ObjectMonitorPanel objectMonitorPanel;
    private ScreenLoaderPanel screenLoaderPanel;

    final public static GameLogger gameLogger = new GameLogger();
    final public static ObjectMonitor objectMonitor = new ObjectMonitor();
    final public static ScreenLoader screenLoader = new ScreenLoader();

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
        gameLogger.setContentPanel(logPanel);


        objectMonitorPanel = new ObjectMonitorPanel();
        objectMonitor.setContentPanel(objectMonitorPanel);


        screenLoaderPanel = new ScreenLoaderPanel();
        screenLoader.setContentPanel(screenLoaderPanel);
    }

    public void switchOnOrOff() {
        SwingUtilities.invokeLater(() -> {
            setVisible(!isVisible());
            GameFramework.app.log("TestTool", "switch to " + (isVisible() ? "visible" : "not visible"));
        });
    }

    public void MonitorObject(Object object) {
        Gdx.app.postRunnable(() -> {
            objectMonitor.MonitorObject(object);
        });
    }

    public void StopMonitorObject(Object object) {
        Gdx.app.postRunnable(() -> {
            objectMonitor.StopMonitorObject(object);
        });
    }

}
