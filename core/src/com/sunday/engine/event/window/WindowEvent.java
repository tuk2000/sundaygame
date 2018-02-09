package com.sunday.engine.event.window;

import com.sunday.engine.event.Event;

public class WindowEvent extends Event {
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public enum Type {
        Resized, Closed, Maximum, Minimum, Hide, Show
    }

    public enum WindowEventSource {
        Game
    }

    private Type type;

    private int width;
    private int height;


    public WindowEvent(Object source, Type type) {
        super(source);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public static WindowEvent newResizeEvent(int width, int height) {
        WindowEvent windowEvent = new WindowEvent(WindowEventSource.Game, Type.Resized);
        windowEvent.width = width;
        windowEvent.height = height;
        return windowEvent;
    }
}
