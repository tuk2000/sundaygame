package com.sunday.game.engine.view.viewlayers;

import com.sunday.game.engine.common.PhysicDefinition;

public class PhysicViewLayer extends SingleComponentLayer<PhysicDefinition> {

    @Override
    public Class getComponentClass() {
        return PhysicDefinition.class;
    }

}
