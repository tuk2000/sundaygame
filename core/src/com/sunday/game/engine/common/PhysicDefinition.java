package com.sunday.game.engine.common;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
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
        return physicReflection != null;
    }

    public void setPhysicReflection(PhysicReflection physicReflection) {
        this.physicReflection = physicReflection;
    }

    public PhysicReflection getPhysicReflection() {
        return physicReflection;
    }

    public void clearPhysicReflection() {
        physicReflection.bodyCreated = false;
        physicReflection.body = null;
        physicReflection.fixture = null;
        physicReflection = null;
    }

    @Override
    public void dispose() {

    }
}
