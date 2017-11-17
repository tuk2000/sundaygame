package com.sunday.game.engine.model;

import com.sunday.game.engine.control.events.EventProcessor;
import com.sunday.game.engine.model.poperty.RoleLabel;

public class Enemy extends RoleModel {
    public Enemy() {
        super(RoleLabel.Enemy);
    }

    @Override
    public EventProcessor getEventProcessor() {
        return null;
    }

    @Override
    public void dispose() {

    }
}
