package com.sunday.engine.physic;

import com.sunday.engine.environment.event.collision.CollisionSignal;
import com.sunday.engine.model.property.PhysicDefinition;
import com.sunday.engine.rule.CustomizedDataCondition;

public class CollisionCondition extends CustomizedDataCondition<PhysicDefinition> {
    public static CollisionCondition collideBetween(PhysicDefinition physicDefinition, Class<PhysicDefinition> physicDefinitionClass) {
        CollisionCondition collisionCondition = new CollisionCondition();
        collisionCondition.setSignals(CollisionSignal.Collision);
        return collisionCondition;
    }
}
