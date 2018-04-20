package com.sunday.engine.physic;

import com.sunday.engine.common.Signal;
import com.sunday.engine.rule.PreAssignedDataCondition;

public class PhysicBodyCondition extends PreAssignedDataCondition<PhysicBody, PhysicBodyContext> {

    public PhysicBodyCondition(PhysicBody physicBody, Signal... signals) {
        super(physicBody, signals);
    }

    public PhysicBodyCondition(PhysicBody physicBody, Class<? extends Signal> signalTypeClass) {
        super(physicBody, signalTypeClass);
    }

    public static PhysicBodyCondition collideBetween(PhysicBody physicBody, Class<PhysicDefinition> physicDefinitionClass) {
        PhysicBodyCondition physicBodyCondition = new PhysicBodyCondition(physicBody, CollisionSignal.PreSolve);
        physicBodyCondition.setPredicate(collisionContext ->
                collisionContext.isOtherBodyFromDefinition(physicDefinitionClass));
        physicBodyCondition.setExtraInfoEntry("Collision Object", physicDefinitionClass.getSimpleName());
        return physicBodyCondition;
    }

    @Override
    protected void generateExtraInfo(PhysicBodyContext context) {
        setExtraInfoEntry("ConditionType", "PhysicBodyCondition");
    }
}
