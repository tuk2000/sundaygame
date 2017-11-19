package com.sunday.game.engine.control.events;


import com.sunday.game.engine.control.events.EventType;

public class Event {
    private Object source;
    private EventType eventType;

    public Event(Object source, EventType eventType) {
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
