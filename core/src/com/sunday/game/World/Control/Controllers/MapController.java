package com.sunday.game.World.Control.Controllers;

import com.sunday.game.World.Control.Controller;
import com.sunday.game.World.Control.EventType;

public class MapController extends Controller {

    public MapController() {
        super(EventType.Input, EventType.Collision);
    }

    @Override
    public void dispose() {

    }
}
