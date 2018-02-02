package com.sunday.engine.events.driver;

import com.sunday.engine.driver.mouse.MouseData;

public class MouseEvent extends DriverEvent {
    private int screenX;
    private int screenY;

    public MouseEvent(MouseData mouseData) {
        super(mouseData);
        this.screenX = mouseData.screenX;
        this.screenY = mouseData.screenY;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    @Override
    public String toString() {
        return " from " + getSource().toString() + " [" + screenX + "," + screenY + "]";
    }
}
