package com.sunday.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.sunday.engine.Engine;
import com.sunday.engine.databank.Port;
import com.sunday.engine.environment.driver.DriverContext;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardCondition;
import com.sunday.engine.environment.driver.mouse.Mouse;
import com.sunday.engine.environment.driver.mouse.MouseCondition;
import com.sunday.engine.environment.time.AnimationSetting;
import com.sunday.engine.environment.time.Timer;
import com.sunday.engine.environment.time.TimerCondition;
import com.sunday.engine.environment.time.TimerContext;
import com.sunday.engine.examples.Label;
import com.sunday.engine.examples.Role;
import com.sunday.engine.examples.enemy.SawAnimation;
import com.sunday.engine.model.AbstractModel;
import com.sunday.engine.model.property.viewlayers.TextureViewLayer;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.scenario.Scenario;
import com.sunday.engine.scenario.ScopeType;
import com.sunday.game.framework.GameFramework;

public class DemoBallRolling implements Screen {


    private Engine engine;
    private Scenario scenario;
    private SawAnimation sawAnimation = new SawAnimation();
    private Timer sawRandomMovingTimer = new Timer();
    private Rule<TimerContext<Timer>> sawAnimationRule = new Rule<>(TimerCondition.animationTimerCondition(), new Reaction<TimerContext<Timer>>() {
        @Override
        public void accept(TimerContext<Timer> timerContext) {
            Timer timer = timerContext.getData();
            sawAnimation.setStateTime(timer.lastTriggeredTime);
        }
    });

    public DemoBallRolling() {
        engine = new Engine();
        AbstractModel backGroundModel = new AbstractModel() {
            @Override
            protected void disconnectWithInternal(Port port) {

            }

            @Override
            protected void connectWithInternal(Port port) {
                outlook.dimension.set(1000, 1000);
                outlook.viewLayers.add(new TextureViewLayer<>(GameFramework.Resource.getAsset("buttons/buttons.png")));
                movement.position.set(0, 0);
                port.addDataInstance(sawAnimationRule);
            }

            @Override
            public void dispose() {

            }
        };

        Role backGroundRole = new Role(Label.Hero, backGroundModel);

        AbstractModel squareModel = new SquareModel();

        Role heroRole = new Role(Label.Hero, squareModel);

        scenario = new Scenario(ScopeType.EntireLevel);
        scenario.addRole(backGroundRole);
        scenario.addRole(heroRole);
        sawRandomMovingTimer.setPeriod(AnimationSetting.FrameDuration);
        for (int i = 0; i < 10; i++) {
//            AbstractModel sawModel = new SawModel((float) ((Math.random() - 0.5) * 1000), (float) ((Math.random() - 0.5) * 1000));
            AbstractModel sawModel = new SawModel((float) (Math.random() * 500), (float) (Math.random() * 500));
            Role movingSaw = new Role(Label.Enemy, sawModel);
            scenario.addRole(movingSaw);
        }

        engine.getScenarioSystem().setRoot(scenario);
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


    private class SquareModel extends AbstractModel {

        Rule<DriverContext<KeyBoard>> moveRule = new Rule<>(KeyBoardCondition.keyPressed("x"), new Reaction<DriverContext<KeyBoard>>() {
            @Override
            public void accept(DriverContext<KeyBoard> keyBoardDriverContext) {
                movement.position.add(10, 10);
                physicBody.forceMoveTo(movement.position);
            }
        });
        Rule<DriverContext<Mouse>> followMouseRule = new Rule<>(MouseCondition.mouseDragged(), new Reaction<DriverContext<Mouse>>() {
            @Override
            public void accept(DriverContext<Mouse> mouseDriverContext) {
                Mouse mouse = mouseDriverContext.getData();
                movement.position.set(mouse.screenX, Gdx.graphics.getHeight() - mouse.screenY);
                physicBody.forceMoveTo(movement.position);
            }
        });

        public SquareModel() {
            outlook.dimension.set(20, 20);
            outlook.viewLayers.add(new TextureViewLayer<>(GameFramework.Resource.getAsset("buttons/button.png")));
            movement.position.set(100, 100);

            FixtureDef fixtureDef = new FixtureDef();
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(10, 10);
            fixtureDef.shape = shape;
            fixtureDef.density = 1.0f;
            physicDefinition.addFixtureDef(fixtureDef);

            BodyDef bodyDef = new BodyDef();
            bodyDef.gravityScale = 0;
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set(movement.position);
            physicDefinition.setBodyDef(bodyDef);

        }

        @Override
        protected void disconnectWithInternal(Port port) {

        }

        @Override
        protected void connectWithInternal(Port port) {
            port.addDataInstance(moveRule);
            port.addDataInstance(followMouseRule);
        }

        @Override
        public void dispose() {

        }
    }

    private class SawModel extends AbstractModel {
        private TextureViewLayer sawTextureViewLayer = new TextureViewLayer(GameFramework.Resource.getAsset("saws/saw1.png"));

        private Rule<TimerContext<Timer>> sawMovingRule = new Rule<>(new TimerCondition<>(sawRandomMovingTimer), new Reaction<TimerContext<Timer>>() {
            @Override
            public void accept(TimerContext<Timer> timerContext) {
                sawTextureViewLayer.updateTexture(sawAnimation.getKeyFrame());
                movement.position.add((float) (Math.random() - 0.5) * 10, (float) (Math.random() - 0.5) * 10);
                physicBody.forceMoveTo(movement.position);
            }
        });

        public SawModel(float x, float y) {
            movement.position.set(x, y);
            outlook.dimension.set(40, 40);
            outlook.viewLayers.add(sawTextureViewLayer);

            FixtureDef fixtureDef = new FixtureDef();
            CircleShape circleShape = new CircleShape();
            circleShape.setRadius(20);
            fixtureDef.shape = circleShape;
            fixtureDef.density = 0.5f;
            fixtureDef.friction = 0.2f;
            physicDefinition.addFixtureDef(fixtureDef);
            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(movement.position);
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.gravityScale = 0;
            physicDefinition.setBodyDef(bodyDef);
        }

        @Override
        protected void disconnectWithInternal(Port port) {

        }

        @Override
        protected void connectWithInternal(Port port) {
            port.addDataInstance(sawMovingRule);
        }

        @Override
        public void dispose() {

        }
    }
}
