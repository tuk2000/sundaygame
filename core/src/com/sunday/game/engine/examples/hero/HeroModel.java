package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.model.AbstractModel;

public class HeroModel extends AbstractModel {
    private HeroEventProcessor heroEventProcessor;

    public HeroModel() {
        heroEventProcessor = new HeroEventProcessor(this);
        outlook.shape = Shape.Type.Polygon;
        outlook.dimension.set(16.f, 32.f);
        movementState.position.set(32, 32);

        generatePhysicDefinition();
    }

    @Override
    protected void generatePhysicDefinition() {
        BodyDef bodyDef = physicReflection.bodyDef;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(movementState.position);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setRadius(1.0f);
        polygonShape.setAsBox(outlook.dimension.x, outlook.dimension.y);

        FixtureDef fixtureDef = physicReflection.fixtureDef;
        fixtureDef.shape = polygonShape;
        fixtureDef.friction = .1f;
        fixtureDef.restitution = 0.2f;
        fixtureDef.density = 0.1f;

    }

    @Override
    public EventProcessor getEventProcessor() {
        return heroEventProcessor;
    }

    @Override
    public void dispose() {

    }
}
