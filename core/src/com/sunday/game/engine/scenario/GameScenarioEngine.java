package com.sunday.game.engine.scenario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.scenario.eventpocess.GameInputEventTransfer;
import com.sunday.game.engine.scenario.eventpocess.ScenarioEventDispatcher;
import com.sunday.game.engine.scenario.render.ScenarioRenderer;

public class GameScenarioEngine implements Disposable {
    private GameScenario Root;
    private GameScenario screenScenario;
    private ScenarioEventDispatcher scenarioEventDispatcher;
    private GameInputEventTransfer gameInputEventTransfer;
    private ScenarioRenderer scenarioRenderer;

    public GameScenarioEngine() {
        scenarioEventDispatcher = new ScenarioEventDispatcher(this);
        gameInputEventTransfer = new GameInputEventTransfer(scenarioEventDispatcher);
        Gdx.input.setInputProcessor(gameInputEventTransfer);
        scenarioRenderer = new ScenarioRenderer(this);
    }

    public void setRootScenario(GameScenario gameScenario) {
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

    public boolean HasRootScenario() {
        return Root != null;
    }

    @Override
    public void dispose() {
        Root.dispose();
        screenScenario.dispose();
        scenarioEventDispatcher.dispose();
        scenarioRenderer.dispose();
    }

    public void render(float delta) {
        scenarioEventDispatcher.dispatchEventQueue();
        scenarioRenderer.render(delta);
    }
}
