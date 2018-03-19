package com.sunday.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.engine.Engine;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.databank.Port;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.engine.driver.mouse.Mouse;
import com.sunday.engine.examples.Label;
import com.sunday.engine.examples.Role;
import com.sunday.engine.model.AbstractModel;
import com.sunday.engine.model.property.viewlayers.TextureViewLayer;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.condition.KeyBoardCondition;
import com.sunday.engine.rule.condition.MouseCondition;
import com.sunday.engine.scenario.Scenario;
import com.sunday.engine.scenario.ScopeType;
import com.sunday.game.framework.GameFramework;

public class DemoBallRolling implements Screen {


    private Engine engine;
    private Scenario scenario;

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
            }

            @Override
            public void dispose() {

            }
        };

        Role backGroundRole = new Role(Label.Hero, backGroundModel);

        AbstractModel heroModel = new AbstractModel() {
            Rule moveRule = new Rule(KeyBoardCondition.keyPressed("x"), new Reaction<KeyBoard>() {
                @Override
                public void accept(KeyBoard keyBoard) {
                    System.out.println("KeyDown");
                    movement.position.add(10, 10);
                    port.broadcast(movement, DataSignal.Modification);
                }
            });
            Rule followMouseRule = new Rule(MouseCondition.mouseDragged(), new Reaction<Mouse>() {
                @Override
                public void accept(Mouse mouse) {
                    System.out.println("MouseDragged");
                    movement.position.set(mouse.screenX, Gdx.graphics.getHeight() - mouse.screenY);
                    port.broadcast(movement, DataSignal.Modification);
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
}
