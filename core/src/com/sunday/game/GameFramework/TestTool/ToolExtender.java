package com.sunday.game.GameFramework.TestTool;

import javax.swing.*;

public interface ToolExtender {
//    void switchDetachMode();//in TestTool or alone
    void updateContent();
    <T extends JComponent> void setContentPanel(T frame);
    <T extends JComponent> T getContentPanel();
}
