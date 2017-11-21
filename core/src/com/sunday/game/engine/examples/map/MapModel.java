package com.sunday.game.engine.examples.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.game.engine.common.Outlook;
import com.sunday.game.engine.common.PhysicDefinition;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.model.AbstractModel;

public class MapModel extends AbstractModel {
    public MapModel() {
        outlook = new Outlook(Shape.Type.Chain, new Vector2(1000, 1000));
        generatePhysicDefinition();
    }

    @Override
    protected void generatePhysicDefinition() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);

        ChainShape chainShape = new ChainShape();
        chainShape.createChain(new Vector2[]{new Vector2(-200, 0), new Vector2(1000, 0)});

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;
        fixtureDef.friction = 2.0f;
        fixtureDef.restitution = 0;
        fixtureDef.density = 15f;

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
