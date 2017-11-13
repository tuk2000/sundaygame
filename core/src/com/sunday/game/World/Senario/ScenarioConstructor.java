package com.sunday.game.World.Senario;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class ScenarioConstructor {
    private GameScenarioEngine gameScenarioEngine;

    public ScenarioConstructor(GameScenarioEngine gameScenarioEngine) {
        this.gameScenarioEngine = gameScenarioEngine;
    }

    public GameScenario construct(ScenarioSetting scenarioSetting) {
        if (!gameScenarioEngine.HasRootScenario()) return null;
        return new GameScenario(new GameScenarioScope(scenarioSetting.scopeType));
    }

    public GameScenario construct() {
        if (!gameScenarioEngine.HasRootScenario()) return null;
        return construct(new ScenarioSetting());
    }

    public GameScenario constructRootScenario(Stage stage) {
        GameScenario rootGameScenario = new GameScenario(new GameScenarioScope(ScopeType.EntireLevel));
        gameScenarioEngine.setRootScenario(rootGameScenario, stage);
        return rootGameScenario;
    }

}
