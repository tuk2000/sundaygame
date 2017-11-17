package com.sunday.game.engine.control;

import com.sunday.game.engine.control.events.EventType;

public class MapController extends Controller {

    public MapController() {
        super(EventType.Input, EventType.Collision);
    }

    @Override
    public void dispose() {

    }
}
