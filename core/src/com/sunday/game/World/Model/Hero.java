package com.sunday.game.World.Model;

import com.sunday.game.World.Control.EventProcessor;
import com.sunday.game.World.Model.Property.RoleLabel;

public abstract class Hero extends RoleModel {
    public Hero(){
        super(RoleLabel.Hero);
    }

    @Override
    public EventProcessor getEventProcessor() {
        return null;
    }

}
