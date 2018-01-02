package com.sunday.game.engine.control.events;

public class MouseEvent extends InputEvent {
    private int screenX;
    private int screenY;

    public MouseEvent(int screenX, int screenY) {
        super(InputSource.Mouse);
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
        return getEventType().name() + " from " + getSource().toString() + " [" + screenX + "," + screenY + "]";
    }
}
