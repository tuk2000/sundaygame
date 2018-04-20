package com.sunday.engine.physic;

import com.sunday.engine.common.context.SystemDataContext;

public class PhysicBodyContext extends SystemDataContext<PhysicBody> {
    private PhysicBody otherPhysicBody;

    public PhysicBodyContext(PhysicBody physicBody) {
        super(physicBody);
    }

    public PhysicBody getOtherPhysicBody() {
        return otherPhysicBody;
    }

    public void setOtherPhysicBody(PhysicBody otherPhysicBody) {
        this.otherPhysicBody = otherPhysicBody;
    }

    public boolean isOtherBodyEquals(PhysicBody physicBody) {
        return physicBody.equals(otherPhysicBody);
    }

    public boolean isOtherBodyFromDefinition(Class<PhysicDefinition> physicDefinitionClass) {
        return otherPhysicBody.physicDefinition.getClass().isAssignableFrom(physicDefinitionClass);
    }

}
