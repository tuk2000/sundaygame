package com.sunday.game.World.Senario;

import com.badlogic.gdx.utils.Disposable;

public class GameScenarioEngine implements Disposable {
    private GameScenario Root;
    private GameScenario screenScenario;
    private ScenarioEventDispatcher scenarioEventDispatcher;

    public GameScenarioEngine() {
        scenarioEventDispatcher = new ScenarioEventDispatcher();
    }

    protected void setRootScenario(GameScenario gameScenario) {
        Root = gameScenario;
        screenScenario = new GameScenario(new GameScenarioScope(ScopeType.FullScreen));
        Root.addKid(screenScenario);
    }

    public GameScenario getRootScenario() {
        return Root;
    }

    public GameScenario getScreenScenario() {
        return screenScenario;
    }

    protected boolean HasRootScenario() {
        return Root != null;
    }

    @Override
    public void dispose() {
        Root.dispose();
        screenScenario.dispose();
    }
}
