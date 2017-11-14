package com.sunday.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sunday.game.GameFramework.FocusedScreen;
import com.sunday.game.World.Model.Property.RoleLabel;
import com.sunday.game.World.Senario.*;


public class GameTest extends FocusedScreen {

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
    private ScenarioRenderer scenarioRenderer;

    public GameTest() {
        gameScenarioEngine = new GameScenarioEngine();
        Stage stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        ScenarioConstructor scenarioConstructor = new ScenarioConstructor(gameScenarioEngine);
        RoleConstructor roleConstructor = new RoleConstructor(gameScenarioEngine);
        scenarioRenderer = new ScenarioRenderer(stage, gameScenarioEngine);
        GameScenario gameScenario = scenarioConstructor.constructRootScenario(stage);
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
        scenarioRenderer.render(delta);
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
        scenarioRenderer.dispose();
        gameScenarioEngine.dispose();
    }

    @Override
    public InputAdapter getInputAdapter() {
        return inputAdapter;
    }
}
