package com.sunday.game.engine.view.viewlayers;

import com.sunday.game.engine.common.PhysicReflection;

public class PhysicViewLayer extends SingleComponentLayer<PhysicReflection> {

    @Override
    public Class getComponentClass() {
        return PhysicReflection.class;
    }

}
