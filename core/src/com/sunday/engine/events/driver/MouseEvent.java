package com.sunday.engine.events.driver;

import com.sunday.engine.common.enums.Driver;

public class MouseEvent extends DriverEvent {
    private int screenX;
    private int screenY;

    public MouseEvent(int screenX, int screenY) {
        super(Driver.Mouse);
        this.screenX = screenX;
        this.screenY = screenY;
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
