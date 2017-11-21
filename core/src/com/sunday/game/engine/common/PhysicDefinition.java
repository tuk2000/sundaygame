package com.sunday.game.engine.common;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public class PhysicDefinition implements Disposable {

    public FixtureDef fixtureDef;
    public BodyDef bodyDef;
    private PhysicReflection physicReflection;

    public PhysicDefinition(FixtureDef fixtureDef, BodyDef bodyDef) {
        this.fixtureDef = fixtureDef;
        this.bodyDef = bodyDef;
    }

    public boolean hasPhysicReflection() {
        return physicReflection == null ? false : physicReflection.bodyCreated;
    }

    public void generatePhysicReflection(World world) {
        if (hasPhysicReflection()) {
            world.destroyBody(physicReflection.body);
            clearPhysicReflection();
        }
        Body body = world.createBody(bodyDef);
        physicReflection = new PhysicReflection();
        physicReflection.bodyCreated = true;
        physicReflection.body = world.createBody(bodyDef);
        physicReflection.fixture = body.createFixture(fixtureDef);
    }

    public PhysicReflection getPhysicReflection() {
        return physicReflection;
    }

    public void clearPhysicReflection() {
        physicReflection.bodyCreated = false;
        physicReflection.body = null;
        physicReflection.fixture = null;
    }

    @Override
    public void dispose() {

    }
}
