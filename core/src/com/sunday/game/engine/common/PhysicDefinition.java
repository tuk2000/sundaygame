package com.sunday.game.engine.common;

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
        physicReflection = new PhysicReflection();
    }

    public boolean hasPhysicReflection() {
        return physicReflection.bodyCreated;
    }

    public void reGeneratePhysicReflection() {
        if (hasPhysicReflection()) {
            generatePhysicReflection(physicReflection.world);
        }
    }

    public void generatePhysicReflection(World world) {
        physicReflection.configure(world, bodyDef, fixtureDef);
    }

    public PhysicReflection getPhysicReflection() {
        return physicReflection;
    }

    public void clearPhysicReflection() {
        physicReflection.clear();
    }

    @Override
    public void dispose() {

    }

}
