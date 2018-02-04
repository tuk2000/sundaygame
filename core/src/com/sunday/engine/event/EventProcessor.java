package com.sunday.engine.event;

public interface EventProcessor<T extends Event> {
    void processEvent(T t);
}
