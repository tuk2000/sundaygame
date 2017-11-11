package com.sunday.tool;

import javax.swing.*;

public abstract class ToolPanel extends JComponent {
    public abstract void updateView();

    public abstract void useContentData(Object contentData);
}
