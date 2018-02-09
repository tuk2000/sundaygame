package com.sunday.engine.event;

import com.sunday.engine.common.Data;

public interface EventProcessor<T extends Event> extends Data {
    void processEvent(T t);
}
