package com.sunday.game.engine.model;

import com.sunday.game.engine.control.events.EventProcessor;

public class DefaultHero extends Hero {
    @Override
    public EventProcessor getEventProcessor() {
        return null;
    }

    @Override
    public void dispose() {

    }
}
