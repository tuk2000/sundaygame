package com.sunday.game.World.Control.Controllers;

import com.sunday.game.World.Control.Controller;
import com.sunday.game.World.Control.EventType;

public class EnemyController extends Controller {

    public EnemyController() {
        super(EventType.Collision);
    }

    @Override
    public void dispose() {

    }
}
