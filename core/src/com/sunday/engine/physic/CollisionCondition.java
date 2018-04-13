package com.sunday.engine.physic;

import com.sunday.engine.common.Signal;
import com.sunday.engine.model.property.PhysicDefinition;
import com.sunday.engine.rule.PreAssignedDataCondition;

public class CollisionCondition extends PreAssignedDataCondition<PhysicDefinition, CollisionContext<PhysicDefinition>> {

    public CollisionCondition(PhysicDefinition physicDefinition, Signal... signals) {
        super(physicDefinition, signals);
    }

    public CollisionCondition(PhysicDefinition physicDefinition, Class<Signal> signalTypeClass) {
        super(physicDefinition, signalTypeClass);
    }

    public static CollisionCondition collideBetween(PhysicDefinition physicDefinition, Class<PhysicDefinition> physicDefinitionClass) {
        CollisionCondition collisionCondition = new CollisionCondition(physicDefinition, CollisionSignal.Collision);
        return collisionCondition;
    }

    @Override
    protected void generateExtraInfo(CollisionContext context) {

    }

    @Override
    public boolean test(CollisionContext collisionContext) {
        return false;
    }
}
