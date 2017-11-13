package com.sunday.game.World.Model;

import com.sunday.game.World.Control.EventProcessor;
import com.sunday.game.World.Model.Property.RoleLabel;

public class MapModel extends RoleModel {
    public MapModel() {
        super(RoleLabel.Map);
    }

    @Override
    public EventProcessor getEventProcessor() {
        return null;
    }
}
