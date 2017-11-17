package com.sunday.game.engine.control;

import com.sunday.game.engine.control.events.EventType;

public class HeroController extends Controller {

    public HeroController() {
        super(EventType.Input, EventType.Collision);
    }

    @Override
    public void dispose() {

    }
}
