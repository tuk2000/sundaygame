package com.sunday.game.world;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.sunday.engine.Engine;
import com.sunday.engine.common.MetaDataContext;
import com.sunday.engine.databank.Port;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardCondition;
import com.sunday.engine.examples.Label;
import com.sunday.engine.examples.Role;
import com.sunday.engine.model.AbstractModel;
import com.sunday.engine.model.property.MovementSignal;
import com.sunday.engine.model.property.PhysicReflection;
import com.sunday.engine.model.property.PhysicReflectionSignal;
import com.sunday.engine.physic.CollisionCondition;
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
            physicReflection.bodyDef.gravityScale = 1.0f;
            physicReflection.bodyDef.position.set(0, 0);
            physicReflection.bodyDef.type = BodyDef.BodyType.DynamicBody;
            physicReflection.bodyDef.linearVelocity.set(1.7f, 1);

            physicReflection.fixtureDef.shape.setRadius(5);
            physicReflection.fixtureDef.restitution = 0.5f;
            physicReflection.fixtureDef.density = 0.05f;
        }

        @Override
        protected void connectWithInternal(Port port) {
            port.addDataInstance(new Rule(KeyBoardCondition.keyPressed("R"), new Reaction<MetaDataContext<KeyBoard>>() {
                @Override
                public void accept(MetaDataContext<KeyBoard> metaDataContext) {
                    float mass = physicReflection.body.getMass();
                    Vector2 reverseImpulse = physicReflection.body.getLinearVelocity().scl(-mass).add(1.7f * mass, 1 * mass);
                    physicReflection.body.applyLinearImpulse(reverseImpulse, physicReflection.body.getWorldCenter(), true);
                    movement.position.set(0, 0);
                    port.broadcast(movement, MovementSignal.ReLocated);
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
            physicReflection.bodyDef.position.set(movement.position);
            physicReflection.bodyDef.type = BodyDef.BodyType.StaticBody;
            physicReflection.bodyDef.gravityScale = 0;
            physicReflection.bodyDef.angle = -0.523f;

            EdgeShape shape = new EdgeShape();
            shape.set(new Vector2(0, 0), new Vector2(100, 100));
            physicReflection.fixtureDef.shape = shape;
            physicReflection.fixtureDef.restitution = 1;
            physicReflection.fixtureDef.friction = 0;
        }

        @Override
        protected void connectWithInternal(Port port) {
            port.addDataInstance(new Rule(CollisionCondition.collideBetween(physicReflection, PhysicReflection.class), new Reaction<MetaDataContext<PhysicReflection>>() {
                @Override
                public void accept(MetaDataContext<PhysicReflection> physicReflectionMetaDataContext) {
                    physicReflection.body.applyLinearImpulse(new Vector2(1.7f, 1), physicReflection.body.getWorldCenter(), true);
                    port.broadcast(physicReflection, PhysicReflectionSignal.Updated);
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
