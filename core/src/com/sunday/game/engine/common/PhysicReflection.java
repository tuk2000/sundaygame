package com.sunday.game.engine.common;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Disposable;

public class PhysicReflection implements Data, Disposable {
    public final FixtureDef fixtureDef = new FixtureDef();
    public final BodyDef bodyDef = new BodyDef();
    public boolean bodyCreated = false;

    public Body body;
    public Fixture fixture;

    public void createFixture() {
        fixture = body.createFixture(fixtureDef);
    }

    @Override
    public void dispose() {

    }
}
