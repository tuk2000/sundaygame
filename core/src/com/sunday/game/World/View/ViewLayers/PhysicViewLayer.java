package com.sunday.game.World.View.ViewLayers;

import com.sunday.game.World.View.EntityPhysicDefinition;

public class PhysicViewLayer extends SingleComponentLayer<EntityPhysicDefinition> {

    @Override
    public Class getComponentClass() {
        return EntityPhysicDefinition.class;
    }

}
