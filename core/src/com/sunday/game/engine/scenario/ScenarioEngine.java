package com.sunday.game.engine.scenario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.scenario.eventpocess.CollisionEventTransfer;
import com.sunday.game.engine.scenario.eventpocess.EventDispatcher;
import com.sunday.game.engine.scenario.eventpocess.InputEventTransfer;
import com.sunday.game.engine.scenario.physicprocess.PhysicSimulator;
import com.sunday.game.engine.scenario.render.ScenarioRenderer;

public class ScenarioEngine implements Disposable {
    private Scenario Root;
    private Scenario screenScenario;
    private EventDispatcher eventDispatcher;
    private InputEventTransfer inputEventTransfer;
    private CollisionEventTransfer collisionEventTransfer;
    private ScenarioRenderer scenarioRenderer;
    private PhysicSimulator physicSimulator;
    private ScenarioAnalyser scenarioAnalyser;

    private boolean running;


    public ScenarioEngine() {
        Root = new Scenario(new ScenarioScope(ScopeType.Game));
        screenScenario = new Scenario(new ScenarioScope(ScopeType.FullScreen));
        Root.addKid(screenScenario);

        eventDispatcher = new EventDispatcher(Root);
        inputEventTransfer = new InputEventTransfer(eventDispatcher);
        collisionEventTransfer = new CollisionEventTransfer(eventDispatcher);
        Gdx.input.setInputProcessor(inputEventTransfer);

        physicSimulator = new PhysicSimulator();
        physicSimulator.setContactListener(collisionEventTransfer);
        scenarioRenderer = new ScenarioRenderer(physicSimulator);
        eventDispatcher.addInternalEventProcessor(scenarioRenderer.getCameraProcessor());
        eventDispatcher.addInternalEventProcessor(scenarioRenderer.getRenderProcessor());
        scenarioAnalyser = new ScenarioAnalyser(scenarioRenderer, physicSimulator);

        running = true;
    }

    public Scenario getRootScenario() {
        return Root;
    }

    public Scenario getScreenScenario() {
        return screenScenario;
    }

    @Override
    public void dispose() {
        running = false;
        eventDispatcher.dispose();
        scenarioRenderer.dispose();
        physicSimulator.dispose();
        scenarioAnalyser.dispose();
        screenScenario.dispose();
        Root.dispose();
    }

    public void render(float delta) {
        if (!running) return;
        eventDispatcher.dispatchEventQueue();
        scenarioAnalyser.analyse(Root);
        physicSimulator.worldStep();
        scenarioRenderer.render(delta);
    }

    public void resize(int width, int height) {
        scenarioRenderer.resizeDisplay(width, height);
    }
}
