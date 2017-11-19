package com.sunday.game.engine.examples.enemy;

import com.sunday.game.engine.control.AbstractController;
import com.sunday.game.engine.control.events.EventType;

public class EnemyController extends AbstractController {

    public EnemyController() {
        super(EventType.Collision);
    }

    @Override
    public void dispose() {

    }
}
