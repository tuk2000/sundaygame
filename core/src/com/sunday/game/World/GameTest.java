package com.sunday.game.world;

import com.badlogic.gdx.Screen;
import com.sunday.game.engine.common.RoleConstructor;
import com.sunday.game.engine.common.RoleLabel;
import com.sunday.game.engine.scenario.Scenario;
import com.sunday.game.engine.scenario.ScenarioConstructor;
import com.sunday.game.engine.scenario.ScenarioEngine;


public class GameTest implements Screen {

    private ScenarioEngine scenarioEngine;

    public GameTest() {
        scenarioEngine = new ScenarioEngine();
        ScenarioConstructor scenarioConstructor = new ScenarioConstructor(scenarioEngine);
        RoleConstructor roleConstructor = new RoleConstructor();
        Scenario scenario = scenarioConstructor.constructRootScenario();
        scenario.addRole(roleConstructor.construct(RoleLabel.Map));
        scenario.addRole(roleConstructor.construct(RoleLabel.Hero));
        scenario.addRole(roleConstructor.construct(RoleLabel.Enemy));
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
