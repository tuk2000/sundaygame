package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.game.engine.common.Outlook;
import com.sunday.game.engine.common.PhysicDefinition;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.model.AbstractModel;

public class HeroModel extends AbstractModel {
    private HeroEventProcessor heroEventProcessor;

    public HeroModel() {
        heroEventProcessor = new HeroEventProcessor(this);
        outlook = new Outlook(Shape.Type.Polygon, new Vector2(16.0f, 32.0f));
        movementState.position.set(32, 32);

        generatePhysicDefinition();
    }

    @Override
    protected void generatePhysicDefinition() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(movementState.position);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setRadius(1.0f);
        polygonShape.setAsBox(outlook.dimension.x, outlook.dimension.y);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.friction = .1f;
        fixtureDef.restitution = 0.5f;
        fixtureDef.density = 1.0f;

        physicDefinition = new PhysicDefinition(fixtureDef, bodyDef);
    }

    @Override
    public EventProcessor getEventProcessor() {
        return heroEventProcessor;
    }

    @Override
    public void dispose() {

    }
}
