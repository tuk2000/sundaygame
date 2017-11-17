package com.sunday.game.world;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.sunday.game.engine.model.poperty.RoleLabel;
import com.sunday.game.engine.scenario.GameScenario;
import com.sunday.game.engine.scenario.GameScenarioEngine;
import com.sunday.game.engine.scenario.ScenarioConstructor;
import com.sunday.game.engine.scenario.role.RoleConstructor;


public class GameTest implements Screen {

    private InputAdapter inputAdapter = new InputAdapter() {
        @Override
        public boolean keyDown(int keycode) {
            switch (keycode) {
                case Input.Keys.LEFT:

                    break;
                case Input.Keys.RIGHT:

                    break;
                case Input.Keys.UP:

                    break;
                case Input.Keys.DOWN:

                    break;
            }
            return true;
        }
    };


    private GameScenarioEngine gameScenarioEngine;

    public GameTest() {
        gameScenarioEngine = new GameScenarioEngine();
        ScenarioConstructor scenarioConstructor = new ScenarioConstructor(gameScenarioEngine);
        RoleConstructor roleConstructor = new RoleConstructor(gameScenarioEngine);
        GameScenario gameScenario = scenarioConstructor.constructRootScenario();
        gameScenario.addRole(roleConstructor.construct(RoleLabel.Map));
        gameScenario.addRole(roleConstructor.construct(RoleLabel.Hero));
        gameScenario.addRole(roleConstructor.construct(RoleLabel.Enemy));
        scenarioConstructor.dispose();
        roleConstructor.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gameScenarioEngine.render(delta);
    }

    @Override
    public void resize(int width, int height) {

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
        gameScenarioEngine.dispose();
    }

}
