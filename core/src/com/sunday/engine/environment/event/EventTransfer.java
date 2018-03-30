package com.sunday.engine.environment.event;


import com.sunday.engine.common.Data;

import java.util.ArrayList;
import java.util.List;

public class EventTransfer implements Data {
    private List<Event> bufferedEvents = new ArrayList<>();
    //dummy EventDispatcher to buffer events , when the real EventDispatcher not available
    private EventDispatcher dummyDispatcher = new EventDispatcher() {
        @Override
        public void dispatch(Event event) {
            bufferedEvents.add(event);
        }
    };
    protected EventDispatcher eventDispatcher = dummyDispatcher;

    protected void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
        bufferedEvents.forEach(event -> eventDispatcher.dispatch(event));
    }

    public void useDummyDispatcher() {
        eventDispatcher = dummyDispatcher;
    }
}
