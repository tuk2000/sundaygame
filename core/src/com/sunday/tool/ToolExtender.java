package com.sunday.tool;

public class ToolExtender<T extends ToolExtenderUIController> {
    protected T UIController;
    protected UIControllerBuffer uiControllerBuffer;

    protected ToolExtender() {
        uiControllerBuffer = new UIControllerBuffer();
    }

    public T getUIController() {
        return UIController;
    }

    public void setUIController(T UIController) {
        this.UIController = UIController;
        flushBuffer();
    }

    protected void flushBuffer() {
        if (UIController != null)
            uiControllerBuffer.flush(UIController);
    }
}
