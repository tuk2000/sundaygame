package com.sunday.game.engine.scenario;

import com.badlogic.gdx.utils.Disposable;

public class ScenarioConstructor implements Disposable {

    public Scenario construct(ScenarioSetting scenarioSetting) {
        return new Scenario(new ScenarioScope(scenarioSetting.scopeType));
    }

    public Scenario construct() {
        return construct(new ScenarioSetting());
    }

    @Override
    public void dispose() {

    }
}
