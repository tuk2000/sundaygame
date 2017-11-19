package com.sunday.game.engine.common;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Disposable;

public class EntityPhysicDefinition implements Disposable {
    public FixtureDef fixtureDef;
    public BodyDef bodyDef;
    public Shape shape;

    public EntityPhysicDefinition() {
    }

    public EntityPhysicDefinition(FixtureDef fixtureDef, BodyDef bodyDef, Shape shape) {
        this.fixtureDef = fixtureDef;
        this.bodyDef = bodyDef;
        this.shape = shape;
    }

    @Override
    public void dispose() {

    }
}
