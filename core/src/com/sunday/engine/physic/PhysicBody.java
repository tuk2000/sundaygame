package com.sunday.engine.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.data.SystemData;
import com.sunday.engine.common.propertyholder.Resettable;
import com.sunday.engine.common.signal.DataSignal;

@DataMark(type = DataType.System, signalClass = {DataSignal.class, CollisionSignal.class}, contextClass = PhysicBodyContext.class)
public class PhysicBody implements SystemData, Resettable {
    protected PhysicDefinition physicDefinition;
    private boolean bodyCreated;
    private Body body;

    public PhysicBody() {
        bodyCreated = false;
    }

    public PhysicBody(PhysicDefinition physicDefinition) {
        this();
        this.physicDefinition = physicDefinition;
    }


    public Fixture createFixture(FixtureDef def) {
        return body.createFixture(def);
    }

    public Fixture createFixture(Shape shape, float density) {
        return body.createFixture(shape, density);
    }

    public void destroyFixture(Fixture fixture) {
        body.destroyFixture(fixture);
    }

    public void setTransform(Vector2 position, float angle) {
        body.setTransform(position, angle);
    }

    public void setTransform(float x, float y, float angle) {
        body.setTransform(x, y, angle);
    }

    public Transform getTransform() {
        return body.getTransform();
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public float getAngle() {
        return body.getAngle();
    }

    public Vector2 getWorldCenter() {
        return body.getWorldCenter();
    }

    public Vector2 getLocalCenter() {
        return body.getLocalCenter();
    }

    public void setLinearVelocity(float vX, float vY) {
        body.setLinearVelocity(vX, vY);
    }

    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    public void setLinearVelocity(Vector2 v) {
        body.setLinearVelocity(v);
    }

    public float getAngularVelocity() {
        return body.getAngularVelocity();
    }

    public void setAngularVelocity(float omega) {
        body.setAngularVelocity(omega);
    }

    public void applyForce(Vector2 force, Vector2 point, boolean wake) {
        body.applyForce(force, point, wake);
    }

    public void applyForce(float forceX, float forceY, float pointX, float pointY, boolean wake) {
        body.applyForce(forceX, forceY, pointX, pointY, wake);
    }

    public void applyForceToCenter(Vector2 force, boolean wake) {
        body.applyForceToCenter(force, wake);
    }

    public void applyForceToCenter(float forceX, float forceY, boolean wake) {
        body.applyForceToCenter(forceX, forceY, wake);
    }

    public void applyTorque(float torque, boolean wake) {
        body.applyTorque(torque, wake);
    }

    public void applyLinearImpulse(Vector2 impulse, Vector2 point, boolean wake) {
        body.applyLinearImpulse(impulse, point, wake);
    }

    public void applyLinearImpulse(float impulseX, float impulseY, float pointX, float pointY, boolean wake) {
        body.applyLinearImpulse(impulseX, impulseY, pointX, pointY, wake);
    }

    public void applyAngularImpulse(float impulse, boolean wake) {
        body.applyAngularImpulse(impulse, wake);
    }

    public float getMass() {
        return body.getMass();
    }

    public float getInertia() {
        return body.getInertia();
    }

    public MassData getMassData() {
        return body.getMassData();
    }

    public void setMassData(MassData data) {
        body.setMassData(data);
    }

    public void resetMassData() {
        body.resetMassData();
    }

    public Vector2 getWorldPoint(Vector2 localPoint) {
        return body.getWorldPoint(localPoint);
    }

    public Vector2 getWorldVector(Vector2 localVector) {
        return body.getWorldVector(localVector);
    }

    public Vector2 getLocalPoint(Vector2 worldPoint) {
        return body.getLocalPoint(worldPoint);
    }

    public Vector2 getLocalVector(Vector2 worldVector) {
        return body.getLocalVector(worldVector);
    }

    public Vector2 getLinearVelocityFromWorldPoint(Vector2 worldPoint) {
        return body.getLinearVelocityFromWorldPoint(worldPoint);
    }

    public Vector2 getLinearVelocityFromLocalPoint(Vector2 localPoint) {
        return body.getLinearVelocityFromLocalPoint(localPoint);
    }

    public float getLinearDamping() {
        return body.getLinearDamping();
    }

    public void setLinearDamping(float linearDamping) {
        body.setLinearDamping(linearDamping);
    }

    public float getAngularDamping() {
        return body.getAngularDamping();
    }

    public void setAngularDamping(float angularDamping) {
        body.setAngularDamping(angularDamping);
    }

    public BodyDef.BodyType getType() {
        return body.getType();
    }

    public boolean isBullet() {
        return body.isBullet();
    }

    public void setBullet(boolean flag) {
        body.setBullet(flag);
    }

    public boolean isSleepingAllowed() {
        return body.isSleepingAllowed();
    }

    public void setSleepingAllowed(boolean flag) {
        body.setSleepingAllowed(flag);
    }

    public boolean isAwake() {
        return body.isAwake();
    }

    public void setAwake(boolean flag) {
        body.setAwake(flag);
    }

    public boolean isActive() {
        return body.isActive();
    }

    public void setActive(boolean flag) {
        body.setActive(flag);
    }

    public boolean isFixedRotation() {
        return body.isFixedRotation();
    }

    public void setFixedRotation(boolean flag) {
        body.setFixedRotation(flag);
    }

    public Array<Fixture> getFixtureList() {
        return body.getFixtureList();
    }

    public Array<JointEdge> getJointList() {
        return body.getJointList();
    }

    public float getGravityScale() {
        return body.getGravityScale();
    }

    public void setGravityScale(float scale) {
        body.setGravityScale(scale);
    }

    public World getWorld() {
        return body.getWorld();
    }

    public void forceMoveTo(Vector2 vector2) {
        if (bodyCreated) {
            body.setTransform(vector2.x, vector2.y, body.getAngle());
        }
    }

    public boolean isBodyCreated() {
        return bodyCreated;
    }

    public void createWithDefinition(World world) {
        body = physicDefinition.createBody(world);
        body.setUserData(physicDefinition);
        body.getFixtureList().forEach(fixture -> fixture.setUserData(this));
        bodyCreated = true;
    }

    public void destroyWithDefinition(World world) {
        world.destroyBody(body);
        body = null;
        bodyCreated = false;
    }

    @Override
    public void reset() {
        if (bodyCreated)
            body.getWorld().destroyBody(body);
        body = null;
        bodyCreated = false;
    }

}
