package com.sunday.game.engine.model;

import com.sunday.game.engine.control.events.EventProcessor;
import com.sunday.game.engine.model.poperty.RoleLabel;

public abstract class Hero extends RoleModel {
    public Hero() {
        super(RoleLabel.Hero);
    }

    @Override
    public EventProcessor getEventProcessor() {
        return null;
    }

}
