package com.sunday.game.engine.scenario;

import com.badlogic.gdx.utils.Disposable;

public class ScenarioConstructor implements Disposable {
    private ScenarioEngine scenarioEngine;

    public ScenarioConstructor(ScenarioEngine scenarioEngine) {
        this.scenarioEngine = scenarioEngine;
    }

    public Scenario construct(ScenarioSetting scenarioSetting) {
        if (!scenarioEngine.HasRootScenario()) return null;
        return new Scenario(new ScenarioScope(scenarioSetting.scopeType));
    }

    public Scenario construct() {
        if (!scenarioEngine.HasRootScenario()) return null;
        return construct(new ScenarioSetting());
    }

    public Scenario constructRootScenario() {
        Scenario rootScenario = new Scenario(new ScenarioScope(ScopeType.EntireLevel));
        scenarioEngine.setRootScenario(rootScenario);
        return rootScenario;
    }

    @Override
    public void dispose() {

    }
}
