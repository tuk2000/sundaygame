package com.sunday.game.engine.examples.enemy;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.game.engine.common.viewlayers.MapViewLayer;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.databank.port.HolderPort;
import com.sunday.game.engine.model.AbstractModel;

public class EnemyModel extends AbstractModel {

    public EnemyModel() {
        outlook.shape = Shape.Type.Circle;
        outlook.dimension.set(40.0f, 40.0f);
        movementState.position.set(112, 32);

        BodyDef bodyDef = physicReflection.bodyDef;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(movementState.position);

        CircleShape circle = new CircleShape();
        circle.setRadius(20.0f);

        FixtureDef fixtureDef = physicReflection.fixtureDef;
        fixtureDef.shape = circle;
        fixtureDef.friction = .75f;
        fixtureDef.restitution = 0.5f;
        fixtureDef.density = 10f;
    }

    @Override
    public EventProcessor getEventProcessor() {
        return null;
    }

    @Override
    protected void initDataSynchronize(HolderPort holderPort) {

    }

    @Override
    public void dispose() {

    }
}
