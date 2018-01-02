package com.sunday.game.engine.common;

import com.badlogic.gdx.physics.box2d.*;

public class PhysicReflection {
    public boolean bodyCreated = false;
    public World world;
    public Body body;
    public Fixture fixture;

    public void configure(World world, BodyDef bodyDef, FixtureDef fixtureDef) {
        clear();
        this.world = world;
        bodyCreated = true;
        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);
    }

    public void clear() {
        if (bodyCreated) {
            world.destroyBody(body);
            bodyCreated = false;
            body = null;
            fixture = null;
        }
    }
}
