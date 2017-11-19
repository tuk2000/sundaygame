package com.sunday.game.engine.examples.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.game.engine.common.Outlook;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.model.AbstractModel;

public class EnemyModel extends AbstractModel {
    public EnemyModel() {
        Outlook outlook = new Outlook(Shape.Type.Circle, new Vector2(40.0f, 40.0f));
        setOutlook(outlook);
        movementState.position.set(112,32);
    }

    @Override
    public EventProcessor getEventProcessor() {
        return null;
    }

    @Override
    public void dispose() {

    }
}
