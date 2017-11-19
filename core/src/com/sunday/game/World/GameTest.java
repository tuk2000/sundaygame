package com.sunday.game.world;

import com.badlogic.gdx.Screen;
import com.sunday.game.engine.common.enums.Label;
import com.sunday.game.engine.examples.RoleConstructor;
import com.sunday.game.engine.scenario.Scenario;
import com.sunday.game.engine.scenario.ScenarioConstructor;
import com.sunday.game.engine.scenario.ScenarioEngine;


public class GameTest implements Screen {

    private ScenarioEngine scenarioEngine;

    public GameTest() {
        scenarioEngine = new ScenarioEngine();
        Scenario rootScenario = scenarioEngine.getRootScenario();

        ScenarioConstructor scenarioConstructor = new ScenarioConstructor();
        RoleConstructor roleConstructor = new RoleConstructor();

        rootScenario.addRole(roleConstructor.construct(Label.Map));
        rootScenario.addRole(roleConstructor.construct(Label.Hero));
        rootScenario.addRole(roleConstructor.construct(Label.Enemy));
        scenarioConstructor.dispose();
        roleConstructor.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        scenarioEngine.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        scenarioEngine.resize(width, height);
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
        scenarioEngine.dispose();
    }

}
