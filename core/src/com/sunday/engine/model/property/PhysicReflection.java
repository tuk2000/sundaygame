package com.sunday.engine.model.property;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sunday.engine.common.MetaData;

public class PhysicReflection implements MetaData {
    public final FixtureDef fixtureDef = new FixtureDef();
    public final BodyDef bodyDef = new BodyDef();
    public Fixture fixture;
    public Body body;
    public boolean bodyCreated = false;
    public Object owner;
    private Shape dummyShape = new CircleShape();

    public PhysicReflection(Object owner) {
        fixtureDef.shape = dummyShape;
        this.owner = owner;
    }

    public void forceMoveTo(Vector2 vector2) {
        if (bodyCreated) {
            body.setTransform(vector2.x, vector2.y, body.getAngle());
        }
    }

    @Override
    public void reset() {
        fixtureDef.shape.dispose();
        dummyShape.dispose();
        dummyShape = new CircleShape();
        fixtureDef.shape = dummyShape;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0;
        fixtureDef.density = 0;
        fixtureDef.isSensor = false;
        fixtureDef.filter.categoryBits = 0x0001;
        fixtureDef.filter.maskBits = -1;
        fixtureDef.filter.groupIndex = 0;

        fixture = null;

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        bodyDef.angle = 0;
        bodyDef.linearVelocity.set(0, 0);
        bodyDef.angularVelocity = 0;
        bodyDef.linearDamping = 0;
        bodyDef.angularDamping = 0;
        bodyDef.allowSleep = true;
        bodyDef.awake = true;
        bodyDef.fixedRotation = false;
        bodyDef.bullet = false;
        bodyDef.active = true;
        bodyDef.gravityScale = 1;

        if (body != null)
            body.getWorld().destroyBody(body);
        body = null;

        bodyCreated = false;
    }
}
