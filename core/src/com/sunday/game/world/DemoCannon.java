package com.sunday.game.world;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sunday.engine.Engine;
import com.sunday.engine.databank.Port;
import com.sunday.engine.environment.driver.DriverContext;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardCondition;
import com.sunday.engine.examples.Label;
import com.sunday.engine.examples.Role;
import com.sunday.engine.model.AbstractModel;
import com.sunday.engine.physic.PhysicBody;
import com.sunday.engine.physic.PhysicBodyCondition;
import com.sunday.engine.physic.PhysicBodyContext;
import com.sunday.engine.physic.PhysicDefinition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.scenario.Scenario;
import com.sunday.engine.scenario.ScenarioSystem;
import com.sunday.engine.scenario.ScopeType;

public class DemoCannon implements Screen {
    private Engine engine;

    public DemoCannon() {
        engine = new Engine();
        ScenarioSystem scenarioSystem = engine.getScenarioSystem();
        Scenario scenario = new Scenario(ScopeType.Game);
        Role cannon = new Role(Label.Hero, new CannonModel());
        Role bomb = new Role(Label.Hero, new BombModel());
        scenario.addRole(cannon);
        scenario.addRole(bomb);
        scenarioSystem.setRoot(scenario);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        engine.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        engine.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        engine.dispose();
    }

    private class BombModel extends AbstractModel {

        public BombModel() {
            BodyDef bodyDef = new BodyDef();
            bodyDef.gravityScale = 1.0f;
            bodyDef.position.set(0, 0);
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.linearVelocity.set(17f, 10);
            physicDefinition.setBodyDef(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            CircleShape circleShape = new CircleShape();
            circleShape.setRadius(50);
            fixtureDef.shape = circleShape;
            fixtureDef.restitution = 0.5f;
            fixtureDef.density = 0.05f;
            physicDefinition.addFixtureDef(fixtureDef);
        }

        @Override
        protected void connectWithInternal(Port port) {
            port.addDataInstance(new Rule<>(KeyBoardCondition.keyPressed("r"), new Reaction<DriverContext<KeyBoard>>() {
                @Override
                public void accept(DriverContext<KeyBoard> keyBoardDriverContext) {
                    float mass = physicBody.getMass();
                    Vector2 reverseImpulse = physicBody.getLinearVelocity().scl(-mass).add(17f * mass, 10 * mass);
                    physicBody.applyLinearImpulse(reverseImpulse, physicBody.getWorldCenter(), true);
                    movement.position.set(0, 0);
                    physicBody.forceMoveTo(movement.position);
                    System.out.println("BombModel---" + this + "---" + keyBoardDriverContext.getSignal());
                }
            }));
        }

        @Override
        protected void disconnectWithInternal(Port port) {

        }

        @Override
        public void dispose() {

        }
    }

    private class CannonModel extends AbstractModel {
        public CannonModel() {
            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(movement.position);
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.gravityScale = 0;
            bodyDef.angle = -0.523f;
            physicDefinition.setBodyDef(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            PolygonShape shape = new EdgeShape();
            shape.setAsBox(new Vector2(0, 0), new Vector2(1000, 1000));
            fixtureDef.shape = shape;
            fixtureDef.restitution = 1;
            fixtureDef.friction = 0;
            physicDefinition.addFixtureDef(fixtureDef);
            fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.restitution = 1;
            fixtureDef.friction = 0;
            shape.set(new Vector2(0, 100), new Vector2(1000, 1100));
            physicDefinition.addFixtureDef(fixtureDef);
        }

        @Override
        protected void connectWithInternal(Port port) {
            port.addDataInstance(new Rule<>(PhysicBodyCondition.collideBetween(physicBody, PhysicDefinition.class), new Reaction<PhysicBodyContext>() {
                @Override
                public void accept(PhysicBodyContext physicBodyContext) {
                    PhysicBody otherBody = physicBodyContext.getOtherPhysicBody();
                    otherBody.applyLinearImpulse(new Vector2(17f, 10).scl(otherBody.getMass()), otherBody.getWorldCenter(), true);
                    System.out.println("CannonModel---" + this + "---" + physicBodyContext.getSignal());
                }
            }));
        }

        @Override
        protected void disconnectWithInternal(Port port) {

        }

        @Override
        public void dispose() {

        }
    }
}
