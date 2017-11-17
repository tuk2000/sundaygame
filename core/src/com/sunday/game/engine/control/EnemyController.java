package com.sunday.game.engine.control;

import com.sunday.game.engine.control.events.EventType;

public class EnemyController extends Controller {

    public EnemyController() {
        super(EventType.Collision);
    }

    @Override
    public void dispose() {

    }
}
