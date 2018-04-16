package com.sunday.engine.examples.enemy;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.sunday.engine.databank.Port;
import com.sunday.engine.model.AbstractModel;

public class EnemyModel extends AbstractModel {

    public EnemyModel() {
        outlook.dimension.set(40.0f, 40.0f);
        movement.position.set(112, 32);

        BodyDef bodyDef = physicDefinition.bodyDef;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(movement.position);

        CircleShape circle = new CircleShape();
        circle.setRadius(20.0f);

        FixtureDef fixtureDef = physicDefinition.fixtureDef;
        fixtureDef.shape = circle;
        fixtureDef.friction = .75f;
        fixtureDef.restitution = 0.5f;
        fixtureDef.density = 10f;
    }


    @Override
    protected void connectWithInternal(Port userPort) {

    }

    @Override
    protected void disconnectWithInternal(Port port) {

    }

    @Override
    public void dispose() {

    }
}
