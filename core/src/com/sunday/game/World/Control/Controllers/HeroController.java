package com.sunday.game.World.Control.Controllers;

import com.sunday.game.World.Control.Controller;
import com.sunday.game.World.Control.EventType;

public class HeroController extends Controller {

    public HeroController() {
        super(EventType.Input, EventType.Collision);
    }

    @Override
    public void dispose() {

    }
}
