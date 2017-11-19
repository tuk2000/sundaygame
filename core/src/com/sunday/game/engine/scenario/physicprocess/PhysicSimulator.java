package com.sunday.game.engine.scenario.physicprocess;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.PhysicDefinition;

public class PhysicSimulator implements Disposable {
    protected Vector2 defaultGravity = new Vector2(0, -9.8f);
    protected World world;

    public World getWorld() {
        return world;
    }

    public PhysicSimulator() {
        world = new World(defaultGravity, false);
    }

    public void createBodyInWorld(PhysicDefinition physicDefinition) {
        if (!physicDefinition.bodyCreated) {
            Body body = world.createBody(physicDefinition.bodyDef);
            physicDefinition.body = body;
            physicDefinition.fixture = body.createFixture(physicDefinition.fixtureDef);
            physicDefinition.bodyCreated = true;
        }
    }

    public void createBody(PhysicDefinition... physicDefinitions) {
        for (PhysicDefinition physicDefinition : physicDefinitions) {
            createBodyInWorld(physicDefinition);
        }
    }

    public void setContactListener(ContactListener contactListener) {
        world.setContactListener(contactListener);
    }

    public void worldStep() {
        world.step(1 / 60f, 8, 3);
    }

    @Override
    public void dispose() {
        world.dispose();
    }
}
