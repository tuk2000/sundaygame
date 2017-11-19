package com.sunday.game.engine.model;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.MovementState;
import com.sunday.game.engine.common.Outlook;
import com.sunday.game.engine.common.PhysicDefinition;
import com.sunday.game.engine.common.Property;
import com.sunday.game.engine.control.EventProcessor;

public abstract class AbstractModel implements Disposable {

    public boolean isModified = false;

    public Property property;
    public AbstractReaction reaction;
    public Outlook outlook;
    public PhysicDefinition physicDefinition;
    public MovementState movementState;

    protected void setOutlook(Outlook outlook) {
        this.outlook = outlook;
    }

    public AbstractModel() {
        movementState = new MovementState();
    }

    public abstract EventProcessor getEventProcessor();

}
