package com.sunday.game.engine.view.viewlayers;

import com.sunday.game.engine.view.EntityPhysicDefinition;

public class PhysicViewLayer extends SingleComponentLayer<EntityPhysicDefinition> {

    @Override
    public Class getComponentClass() {
        return EntityPhysicDefinition.class;
    }

}
