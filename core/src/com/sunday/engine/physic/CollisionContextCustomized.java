package com.sunday.engine.physic;

import com.sunday.engine.common.CustomizedDataContext;
import com.sunday.engine.model.property.PhysicDefinition;

public class CollisionContextCustomized<P extends PhysicDefinition> extends CustomizedDataContext<P> {
    public CollisionContextCustomized(P physicReflection) {
        super(physicReflection);
    }
}
