package com.sunday.game.World.Senario;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.sunday.game.World.View.Animation.AnimationSetting;

public class GameScenarioEngine {
    private GameScenario Root;
    private GameScenario screenScenario;
    private ScenarioRenderer scenarioRenderer;
    private ScenarioEventDispatcher scenarioEventDispatcher;
    private Stage stage;

    public GameScenarioEngine() {
        scenarioEventDispatcher = new ScenarioEventDispatcher();
    }

    protected void setRootScenario(GameScenario gameScenario, Stage stage) {
        Root = gameScenario;
        screenScenario = new GameScenario(new GameScenarioScope(ScopeType.FullScreen));
        Root.addKid(screenScenario);
        this.stage = stage;
        scenarioRenderer = new ScenarioRenderer(stage);
    }

    protected boolean HasRootScenario() {
        return Root != null;
    }

    public void render(float delta) {
        AnimationSetting.DeltaTime += delta;
        scenarioRenderer.render(delta);
    }

}
