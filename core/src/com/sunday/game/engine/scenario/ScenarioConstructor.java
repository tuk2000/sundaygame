package com.sunday.game.engine.scenario;

import com.badlogic.gdx.utils.Disposable;

public class ScenarioConstructor implements Disposable {

    public Scenario construct(ScopeType scopeType) {
        return new Scenario(scopeType);
    }

    public Scenario construct() {
        return construct(ScopeType.Game);
    }

    @Override
    public void dispose() {

    }
}
