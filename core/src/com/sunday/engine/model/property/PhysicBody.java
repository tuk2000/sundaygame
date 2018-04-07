package com.sunday.engine.model.property;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.sunday.engine.common.data.SystemRelatedData;
import com.sunday.engine.common.propertyholder.Resettable;

public class PhysicBody implements SystemRelatedData, Resettable {
    public PhysicDefinition physicDefinition;
    private boolean bodyCreated;
    private Body body;
    private Fixture fixture;

    public PhysicBody(PhysicDefinition physicDefinition) {
        this.physicDefinition = physicDefinition;
        bodyCreated = false;
    }


    public float getAngle() {
        return body.getAngle();
    }

    public float getAngularVelocity() {
        return body.getAngularVelocity();
    }

    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public void forceMoveTo(Vector2 vector2) {
        if (bodyCreated) {
            body.setTransform(vector2.x, vector2.y, body.getAngle());
        }
    }

    public boolean isBodyCreated() {
        return bodyCreated;
    }

    public Body getBody() {
        return body;
    }

    public void createBody(World world) {
        body = world.createBody(physicDefinition.bodyDef);
        bodyCreated = true;
    }

    public void destroyBody(World world) {
        world.destroyBody(body);
        body = null;
        bodyCreated = false;
    }

    public void createFixture() {
        fixture = body.createFixture(physicDefinition.fixtureDef);
        fixture.setUserData(physicDefinition);
    }

    public Fixture getFixture() {
        return fixture;
    }

    @Override
    public void reset() {
        if (bodyCreated)
            body.getWorld().destroyBody(body);
        body = null;
        fixture = null;
        bodyCreated = false;
    }

    public float getMass() {
        return body.getMass();
    }

    public Vector2 getWorldCenter() {
        return body.getWorldCenter();
    }

    public void applyLinearImpulse(Vector2 impulse, Vector2 point, boolean wake) {
        body.applyLinearImpulse(impulse, point, wake);
    }
}
