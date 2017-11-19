package com.sunday.game.engine.scenario.physicprocess;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.PhysicDefinition;

import java.util.ArrayList;

public class PhysicSimulator implements Disposable {
    protected Vector2 defaultGravity = new Vector2(0, -9.8f);
    protected World world;
    private ArrayList<PhysicDefinition> physicDefinitions;

    public World getWorld() {
        return world;
    }

    public PhysicSimulator() {
        world = new World(defaultGravity, false);
        physicDefinitions = new ArrayList<>();
    }

    public Body initBodyInWorld(PhysicDefinition physicDefinition) {
        if (physicDefinitions.contains(physicDefinition))
            return null;
        else {
            physicDefinitions.add(physicDefinition);
            Body body = world.createBody(physicDefinition.bodyDef);
            body.createFixture(physicDefinition.fixtureDef);
            return body;
        }
    }

    public void addEntityPhysicDefinition(PhysicDefinition... physicDefinitions) {
        for (PhysicDefinition physicDefinition : physicDefinitions) {
            initBodyInWorld(physicDefinition);
        }
    }

    public boolean hasEntityPhysicDefinition(PhysicDefinition physicDefinition) {
        return physicDefinitions.contains(physicDefinition);
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
