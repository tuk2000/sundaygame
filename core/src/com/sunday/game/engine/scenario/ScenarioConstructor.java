package com.sunday.game.engine.scenario;

import com.badlogic.gdx.utils.Disposable;

public class ScenarioConstructor implements Disposable {
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

    public GameScenario constructRootScenario() {
        GameScenario rootGameScenario = new GameScenario(new GameScenarioScope(ScopeType.EntireLevel));
        gameScenarioEngine.setRootScenario(rootGameScenario);
        return rootGameScenario;
    }

    @Override
    public void dispose() {

    }
}
