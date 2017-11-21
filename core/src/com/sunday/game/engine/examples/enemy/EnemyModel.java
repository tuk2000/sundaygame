package com.sunday.game.engine.examples.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.game.engine.common.Outlook;
import com.sunday.game.engine.common.PhysicDefinition;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.model.AbstractModel;

public class EnemyModel extends AbstractModel {
    public EnemyModel() {
        outlook = new Outlook(Shape.Type.Circle, new Vector2(40.0f, 40.0f));
        movementState.position.set(112, 32);
        generatePhysicDefinition();
    }

    @Override
    protected void generatePhysicDefinition() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(movementState.position);

        CircleShape circle = new CircleShape();
        circle.setRadius(20.0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.friction = .75f;
        fixtureDef.restitution = 0.5f;
        fixtureDef.density = 10f;

        physicDefinition = new PhysicDefinition(fixtureDef, bodyDef);
    }

    @Override
    public EventProcessor getEventProcessor() {
        return null;
    }

    @Override
    public void dispose() {

    }
}
