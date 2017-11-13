package com.sunday.game.World.Senario;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class ScenarioConstructor {
    private boolean hasRootScenario = false;

    public GameScenario construct(ScenarioSetting scenarioSetting) {
        if (!hasRootScenario) return null;
        return new GameScenario(false, new GameScenarioScope(scenarioSetting.scopeType));
    }

    public GameScenario construct() {
        if (!hasRootScenario) return null;
        return construct(new ScenarioSetting());
    }

    public GameScenario constructRootScenario(Stage stage) {
        GameScenario rootGameScenario = new GameScenario(true, new GameScenarioScope(ScopeType.EntireLevel));
        hasRootScenario = true;
        return rootGameScenario;
    }

}
