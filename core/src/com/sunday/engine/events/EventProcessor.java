package com.sunday.engine.events;

public interface EventProcessor<T extends Event> {
    void processEvent(T t);
}
