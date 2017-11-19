package com.sunday.game.engine.common;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Disposable;

public class PhysicDefinition implements Disposable {
    public boolean bodyCreated = false;
    public FixtureDef fixtureDef;
    public BodyDef bodyDef;
    public Body body;
    public Fixture fixture;

    public PhysicDefinition() {

    }

    public PhysicDefinition(FixtureDef fixtureDef, BodyDef bodyDef) {
        this.fixtureDef = fixtureDef;
        this.bodyDef = bodyDef;
    }

    @Override
    public void dispose() {

    }
}
