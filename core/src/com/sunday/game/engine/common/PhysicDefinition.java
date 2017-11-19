package com.sunday.game.engine.common;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Disposable;

public class PhysicDefinition implements Disposable {
    public FixtureDef fixtureDef;
    public BodyDef bodyDef;

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
