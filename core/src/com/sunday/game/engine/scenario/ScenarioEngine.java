package com.sunday.game.engine.scenario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.scenario.eventpocess.EventDispatcher;
import com.sunday.game.engine.scenario.eventpocess.InputEventTransfer;
import com.sunday.game.engine.scenario.render.ScenarioRenderer;

public class ScenarioEngine implements Disposable {
    private Scenario Root;
    private Scenario screenScenario;
    private EventDispatcher eventDispatcher;
    private InputEventTransfer inputEventTransfer;
    private ScenarioRenderer scenarioRenderer;

    public ScenarioEngine() {
        eventDispatcher = new EventDispatcher(this);
        inputEventTransfer = new InputEventTransfer(eventDispatcher);
        Gdx.input.setInputProcessor(inputEventTransfer);
        scenarioRenderer = new ScenarioRenderer(this);
    }

    public void setRootScenario(Scenario scenario) {
        Root = scenario;
        screenScenario = new Scenario(new ScenarioScope(ScopeType.FullScreen));
        Root.addKid(screenScenario);
    }

    public Scenario getRootScenario() {
        return Root;
    }

    public Scenario getScreenScenario() {
        return screenScenario;
    }

    public boolean HasRootScenario() {
        return Root != null;
    }

    @Override
    public void dispose() {
        Root.dispose();
        screenScenario.dispose();
        eventDispatcher.dispose();
        scenarioRenderer.dispose();
    }

    public void render(float delta) {
        eventDispatcher.dispatchEventQueue();
        scenarioRenderer.render(delta);
    }

    public void resize(int width, int height) {
        scenarioRenderer.resizeDisplay(width, height);
    }
}
