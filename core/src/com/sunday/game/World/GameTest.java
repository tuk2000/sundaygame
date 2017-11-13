package com.sunday.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sunday.game.GameFramework.FocusedScreen;
import com.sunday.game.World.Model.Property.RoleLabel;
import com.sunday.game.World.Senario.GameScenario;
import com.sunday.game.World.Senario.RoleConstructor;
import com.sunday.game.World.Senario.ScenarioConstructor;
import com.sunday.game.World.Senario.ScenarioRenderer;
import com.sunday.game.World.View.Animation.AnimationSetting;


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

    private GameScenario gameScenario;
    private ScenarioRenderer scenarioRenderer;

    public GameTest() {
        Stage stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        gameScenario = new ScenarioConstructor().constructRootScenario(stage);
        gameScenario.addMVCGroup(RoleConstructor.construct(RoleLabel.Hero));
        gameScenario.addMVCGroup(RoleConstructor.construct(RoleLabel.Enemy));
        gameScenario.addMVCGroup(RoleConstructor.construct(RoleLabel.Enemy));
        gameScenario.addMVCGroup(RoleConstructor.construct(RoleLabel.Enemy));
        gameScenario.addMVCGroup(RoleConstructor.construct(RoleLabel.Enemy));

        scenarioRenderer = new ScenarioRenderer(stage, gameScenario);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        AnimationSetting.DeltaTime += Gdx.graphics.getDeltaTime();
        scenarioRenderer.render(delta);
        scenarioRenderer.renderWorldStep();
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

    }

    @Override
    public InputAdapter getInputAdapter() {
        return inputAdapter;
    }
}
