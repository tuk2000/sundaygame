package com.sunday.game.engine.model;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
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

    public AbstractModel() {
        movementState = new MovementState();
    }

    protected abstract void generatePhysicDefinition();

    public abstract EventProcessor getEventProcessor();

    public void synchronizeTextureWithPhysic() {
        if (physicDefinition.hasPhysicReflection()) {
            movementState.position.set(physicDefinition.getPhysicReflection().body.getPosition());
            if (outlook.sizeChanged) {
                reConstructBody();
                outlook.sizeChanged = false;
            }
        }
    }

    private void reConstructBody() {
        if (!physicDefinition.hasPhysicReflection()) return;

        physicDefinition.bodyDef.position.set(movementState.position);

        switch (physicDefinition.fixtureDef.shape.getType()) {
            case Chain:
                break;
            case Polygon:
                ((PolygonShape) physicDefinition.fixtureDef.shape).setAsBox(outlook.dimension.x, outlook.dimension.y);
                break;
            case Circle:
                ((CircleShape) physicDefinition.fixtureDef.shape).setRadius((outlook.dimension.x + outlook.dimension.y) / 2);
                break;
            case Edge:
                break;
        }
        physicDefinition.reGeneratePhysicReflection();
    }

}
