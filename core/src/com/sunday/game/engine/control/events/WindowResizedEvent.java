package com.sunday.game.engine.control.events;

public class WindowResizedEvent extends WindowEvent {
    private int width;
    private int height;

    public WindowResizedEvent(int width, int height) {
        super(WindowEventSource.Game, Type.Resized);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return getEventType().name() + " from " + getSource().toString() + getType().toString() + " [" + width + " ," + height + "]";
    }
}
