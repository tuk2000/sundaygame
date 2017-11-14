package com.sunday.game.World.Model;

import com.sunday.game.World.Control.EventProcessor;
import com.sunday.game.World.Model.Property.RoleLabel;

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
