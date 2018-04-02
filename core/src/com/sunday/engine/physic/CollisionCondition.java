package com.sunday.engine.physic;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.environment.event.collision.CollisionSignal;
import com.sunday.engine.model.property.PhysicReflection;
import com.sunday.engine.rule.ContextBuilder;
import com.sunday.engine.rule.DataCondition;

public class CollisionCondition extends DataCondition<PhysicReflection> {
    public static CollisionCondition collideBetween(PhysicReflection physicReflection, Class<PhysicReflection> physicReflectionClass) {
        CollisionCondition collisionCondition = new CollisionCondition();
        collisionCondition.context = ContextBuilder.buildDataContext(physicReflection);
        collisionCondition.setSignals(CollisionSignal.Collision);
        return collisionCondition;
    }

//    @Override
//    public void notify(Data data, Signal signal) {
//        getReaction().accept(data, signal);
//    }
}
