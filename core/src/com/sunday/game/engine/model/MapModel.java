package com.sunday.game.engine.model;

import com.sunday.game.engine.control.events.EventProcessor;
import com.sunday.game.engine.model.poperty.RoleLabel;

public class MapModel extends RoleModel {
    public MapModel() {
        super(RoleLabel.Map);
    }

    @Override
    public EventProcessor getEventProcessor() {
        return null;
    }

    @Override
    public void dispose() {

    }
}
