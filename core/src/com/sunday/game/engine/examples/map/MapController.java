package com.sunday.game.engine.examples.map;

import com.sunday.game.engine.control.AbstractController;
import com.sunday.game.engine.control.events.EventType;

public class MapController extends AbstractController {

    public MapController() {
        super(EventType.Input, EventType.Collision);
    }

    @Override
    public void dispose() {

    }
}
