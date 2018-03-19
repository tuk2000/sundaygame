package com.sunday.game.world;

import com.badlogic.gdx.Screen;
import com.sunday.engine.Engine;
import com.sunday.engine.examples.Label;
import com.sunday.engine.examples.RoleConstructor;
import com.sunday.engine.scenario.Scenario;
import com.sunday.engine.scenario.ScenarioSystem;
import com.sunday.engine.scenario.ScopeType;


public class GameTest implements Screen {

    private Engine engine;

    private ScenarioSystem scenarioSystem;

    public GameTest() {
        engine = new Engine();
        scenarioSystem = engine.getScenarioSystem();

        Scenario testScenario = new Scenario(ScopeType.EntireLevel);

        RoleConstructor roleConstructor = new RoleConstructor();
        testScenario.addRole(roleConstructor.construct(Label.Map));
        testScenario.addRole(roleConstructor.construct(Label.Hero));
        testScenario.addRole(roleConstructor.construct(Label.Enemy));
        roleConstructor.dispose();

        scenarioSystem.setRoot(testScenario);
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
