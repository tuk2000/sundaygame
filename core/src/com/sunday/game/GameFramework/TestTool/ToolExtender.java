package com.sunday.game.GameFramework.TestTool;

public abstract class ToolExtender<T extends ToolPanel> {
    private T contentPanel;

    public void updateContentView() {
        if (contentPanel == null) return;
        contentPanel.updateView();
    }

    public abstract Object getContentData();

    public void setContentPanel(T contentPanel) {
        this.contentPanel = contentPanel;
        this.contentPanel.useContentData(getContentData());
    }

    public T getContentPanel() {
        return contentPanel;
    }
}
