package com.sunday.engine.physic;

import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.model.property.PhysicDefinition;

public class CollisionContext<P extends PhysicDefinition> extends CustomizedDataContext<P> {

    public CollisionContext(P physicReflection) {
        super(physicReflection);
    }
}
