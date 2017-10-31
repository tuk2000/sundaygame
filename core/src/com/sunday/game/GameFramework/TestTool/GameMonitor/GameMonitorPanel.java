package com.sunday.game.GameFramework.TestTool.GameMonitor;

import com.sunday.game.GameFramework.TestTool.ToolPanel;

import javax.swing.*;

public class GameMonitorPanel extends ToolPanel {
    private GameData gameData;
    private JPanel panel;
    private JLabel labFPS;
    private JLabel labFrameId;
    private JLabel labDeltatime;
    private JLabel labMemoryUsage;
    private JTextPane pressEscGoToTextPane;
    private JTextPane pressBackSpaceTurnBackTextPane;
    private JTextPane pressPGoToTextPane;


    @Override
    public void updateView() {
        SwingUtilities.invokeLater(() -> {
            labFPS.setText(String.valueOf(gameData.FramePerSecond));
            labFrameId.setText(String.valueOf(gameData.FrameId));
            labDeltatime.setText(String.valueOf(gameData.DeltaTime));
            labMemoryUsage.setText(String.valueOf(gameData.MemoryUsage).toString());
            // ((GameMonitorPanel) this).updateUI();
        });
    }

    @Override
    public void useContentData(Object contentData) {
        if (contentData instanceof GameData) {
            gameData = (GameData) contentData;
        }
        updateView();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        labFPS = new JLabel();
        labFrameId = new JLabel();
        labDeltatime = new JLabel();
        labMemoryUsage = new JLabel();
    }
}
