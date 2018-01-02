package com.sunday.game.engine.control.events;

public class WindowEvent extends Event {
    public enum Type {
        Resized, Closed, Maximum, Minimum, Hide, Show
    }

    public enum WindowEventSource {
        Game
    }

    private Type type;

    public WindowEvent(Object source, Type type) {
        super(source, EventType.Window);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
