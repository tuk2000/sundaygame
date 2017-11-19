package com.sunday.game.engine.examples.hero;

import com.sunday.game.engine.control.AbstractController;
import com.sunday.game.engine.control.events.EventType;

public class HeroController extends AbstractController {

    public HeroController() {
        super(EventType.Input, EventType.Collision);
    }

    @Override
    public void dispose() {

    }
}
