package com.sunday.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
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
    private float sin30 = (float) Math.sin(Math.PI / 6);
    private float cos30 = (float) Math.cos(Math.PI / 6);

    public DemoCannon() {
        engine = new Engine();
        ScenarioSystem scenarioSystem = engine.getScenarioSystem();
        Scenario scenario = new Scenario(ScopeType.Game);
        Role cannon1 = new Role(Label.Hero, new CannonModel(true));
        Role cannon2 = new Role(Label.Hero, new CannonModel(false));
        Role bomb = new Role(Label.Hero, new BombModel());
        scenario.addRole(cannon1);
        scenario.addRole(cannon2);
        scenario.addRole(bomb);
        scenarioSystem.setRoot(scenario);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //ClearColor White and it needs to be  defined only once before all render
        Gdx.gl.glClearColor(0, 0, 0, 1);
        //clear the screen before anything rendered
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
            bodyDef.linearVelocity.set(10 * cos30, 10 * sin30);
            physicDefinition.setBodyDef(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            CircleShape circleShape = new CircleShape();
            circleShape.setRadius(50);
            fixtureDef.shape = circleShape;
            fixtureDef.restitution = 0;
            fixtureDef.density = 0.05f;
            physicDefinition.addFixtureDef(fixtureDef);
        }

        @Override
        protected void connectWithInternal(Port port) {
            port.addDataInstance(new Rule<>(KeyBoardCondition.keyPressed("r"), new Reaction<DriverContext<KeyBoard>>() {
                @Override
                public void accept(DriverContext<KeyBoard> keyBoardDriverContext) {
                    float mass = physicBody.getMass();
                    Vector2 reverseImpulse = physicBody.getLinearVelocity().scl(-mass);
                    physicBody.applyLinearImpulse(reverseImpulse, physicBody.getWorldCenter(), true);
                    Vector2 impulse = new Vector2(cos30, sin30).scl(mass).scl(10);
                    physicBody.applyLinearImpulse(impulse, physicBody.getWorldCenter(), true);
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
        public CannonModel(boolean top) {
            BodyDef bodyDef = new BodyDef();
            if (top) {
                movement.position.set(new Vector2(-sin30, cos30).scl(50));
            } else {
                movement.position.set(new Vector2(sin30, -cos30).scl(50));
            }
            System.out.println(movement.position.toString());
            bodyDef.position.set(movement.position);
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.gravityScale = 0;
            bodyDef.angle = (float) (Math.PI / 6);
            physicDefinition.setBodyDef(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            EdgeShape shape = new EdgeShape();
            shape.set(new Vector2(0, 0), new Vector2(1000, 0));
            fixtureDef.shape = shape;
            fixtureDef.restitution = 0;
            fixtureDef.friction = 0;
            physicDefinition.addFixtureDef(fixtureDef);
        }

        @Override
        protected void connectWithInternal(Port port) {
            port.addDataInstance(new Rule<>(PhysicBodyCondition.collideBetween(physicBody, PhysicDefinition.class), new Reaction<PhysicBodyContext>() {
                @Override
                public void accept(PhysicBodyContext physicBodyContext) {
                    PhysicBody otherBody = physicBodyContext.getOtherPhysicBody();
                    otherBody.applyForceToCenter(new Vector2(cos30, sin30).scl(20 * otherBody.getMass()), true);
                    System.out.println("CannonModel---" + otherBody + "--- owner " + otherBody.getPhysicDefinition().owner);
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
