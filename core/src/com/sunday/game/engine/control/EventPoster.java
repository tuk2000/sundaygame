package com.sunday.game.engine.control;

import com.sunday.game.engine.control.events.Event;

public interface EventPoster {
    void dispatchEvent(Event event);
}
