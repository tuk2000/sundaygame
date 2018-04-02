package com.sunday.engine.physic;

import com.sunday.engine.common.DataContext;
import com.sunday.engine.model.property.PhysicReflection;

public class CollisionContext<P extends PhysicReflection> extends DataContext<P> {
    public CollisionContext(P physicReflection) {
        super(physicReflection);
    }
}
