package com.sunday.tool;

public class ToolExtender<T extends ToolExtenderUIController> {
    protected T UIController;
    protected UIControllerBuffer uiControllerBuffer;

    protected ToolExtender() {
        uiControllerBuffer = new UIControllerBuffer();
    }

    public void setUIController(T UIController) {
        this.UIController = UIController;
        flushBuffer();
    }

    public T getUIController() {
        return UIController;
    }

    protected void flushBuffer() {
        if (UIController != null)
            uiControllerBuffer.flush(UIController);
    }
}
