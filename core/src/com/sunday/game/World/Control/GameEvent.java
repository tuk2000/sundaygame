package com.sunday.game.World.Control;


public class GameEvent {
    private Object source;
    private EventType eventType;

    public GameEvent(Object source, EventType eventType) {
        this.source = source;
        this.eventType = eventType;
    }

    public Object getSource() {
        return source;
    }

    public EventType getEventType() {
        return eventType;
    }
}
