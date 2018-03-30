package com.sunday.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.engine.Engine;
import com.sunday.engine.databank.Port;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardCondition;
import com.sunday.engine.environment.driver.keyboard.KeyBoardSignal;
import com.sunday.engine.environment.driver.mouse.Mouse;
import com.sunday.engine.environment.driver.mouse.MouseCondition;
import com.sunday.engine.environment.driver.mouse.MouseSignal;
import com.sunday.engine.environment.time.AnimationSetting;
import com.sunday.engine.environment.time.Timer;
import com.sunday.engine.environment.time.TimerCondition;
import com.sunday.engine.environment.time.TimerSignal;
import com.sunday.engine.examples.Label;
import com.sunday.engine.examples.Role;
import com.sunday.engine.examples.enemy.SawAnimation;
import com.sunday.engine.model.AbstractModel;
import com.sunday.engine.model.property.MovementSignal;
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
    private Rule sawAnimationRule = new Rule(TimerCondition.animationTimerCondition(), new Reaction<Timer, TimerSignal>() {
        @Override
        public void accept(Timer timer, TimerSignal timerSignal) {
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
                outlook.shape = Shape.Type.Edge;
                outlook.viewLayers.add(new TextureViewLayer<>(GameFramework.Resource.getAsset("buttons/buttons.png")));
                movement.position.set(0, 0);
                port.addDataInstance(sawAnimationRule);
            }

            @Override
            public void dispose() {

            }
        };

        Role backGroundRole = new Role(Label.Hero, backGroundModel);

        AbstractModel heroModel = new AbstractModel() {
            Rule moveRule = new Rule(KeyBoardCondition.keyPressed("x"), new Reaction<KeyBoard, KeyBoardSignal>() {
                @Override
                public void accept(KeyBoard keyBoard, KeyBoardSignal keyBoardSignal) {
                    System.out.println("KeyDown");
                    movement.position.add(10, 10);
                    port.broadcast(movement, MovementSignal.ReLocated);
                }
            });
            Rule followMouseRule = new Rule(MouseCondition.mouseDragged(), new Reaction<Mouse, MouseSignal>() {
                @Override
                public void accept(Mouse mouse, MouseSignal mouseSignal) {
                    System.out.println("MouseDragged");
                    movement.position.set(mouse.screenX, Gdx.graphics.getHeight() - mouse.screenY);
                    port.broadcast(movement, MovementSignal.ReLocated);
                }
            });

            @Override
            protected void disconnectWithInternal(Port port) {

            }

            @Override
            protected void connectWithInternal(Port port) {
                outlook.dimension.set(20, 20);
                outlook.shape = Shape.Type.Edge;
                outlook.viewLayers.add(new TextureViewLayer<>(GameFramework.Resource.getAsset("buttons/button.png")));
                movement.position.set(100, 100);
                port.addDataInstance(moveRule);
                port.addDataInstance(followMouseRule);
            }

            @Override
            public void dispose() {

            }
        };

        Role heroRole = new Role(Label.Hero, heroModel);


        scenario = new Scenario(ScopeType.EntireLevel);
        scenario.addRole(backGroundRole);
        for (int i = 0; i < 100; i++) {
            AbstractModel sawModel = new SawModel((float) ((Math.random() - 0.5) * 1000), (float) ((Math.random() - 0.5) * 1000));
            Role movingSaw = new Role(Label.Enemy, sawModel);
            scenario.addRole(movingSaw);
        }
        scenario.addRole(heroRole);
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

    private class SawModel extends AbstractModel {
        private TextureViewLayer sawTextureViewLayer = new TextureViewLayer(GameFramework.Resource.getAsset("saws/saw1.png"));
        private Timer timer = new Timer();
        private Rule sawMovingRule = new Rule(TimerCondition.bind(timer), new Reaction<Timer, TimerSignal>() {
            @Override
            public void accept(Timer timer, TimerSignal timerSignal) {
                sawTextureViewLayer.updateTexture(sawAnimation.getKeyFrame());
                movement.position.add((float) (Math.random() - 0.5) * 10, (float) (Math.random() - 0.5) * 10);
                port.broadcast(movement, MovementSignal.ReLocated);
            }
        });

        public SawModel(float x, float y) {
            movement.position.set(x, y);
            timer.setPeriod(AnimationSetting.FrameDuration);
        }

        @Override
        protected void disconnectWithInternal(Port port) {

        }

        @Override
        protected void connectWithInternal(Port port) {
            outlook.dimension.set(40, 40);
            outlook.shape = Shape.Type.Circle;
            outlook.viewLayers.add(sawTextureViewLayer);
            port.addDataInstance(sawMovingRule);
        }

        @Override
        public void dispose() {

        }
    }
}
