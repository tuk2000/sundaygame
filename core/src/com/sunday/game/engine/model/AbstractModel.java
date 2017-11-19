package com.sunday.game.engine.model;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.RoleMovementStatus;
import com.sunday.game.engine.common.RolePresent;
import com.sunday.game.engine.common.RoleProperty;
import com.sunday.game.engine.control.EventProcessor;

public abstract class AbstractModel implements Disposable {

    public boolean isModified = false;

    public RoleProperty property;
    public AbstractReaction reaction;
    public RolePresent rolePresent;
    public RoleMovementStatus roleMovementStatus;

    protected void setRolePresent(RolePresent rolePresent) {
        this.rolePresent = rolePresent;
    }

    public AbstractModel() {
        roleMovementStatus = new RoleMovementStatus();
    }

    public abstract EventProcessor getEventProcessor();

}
