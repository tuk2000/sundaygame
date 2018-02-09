package com.sunday.tool;

public class ToolExtender<T extends ToolExtenderUIController> {
    private T controller;

    public void setToolExtenderController(T controller) {
        this.controller = controller;
    }

    public T getController() {
        return controller;
    }
}
