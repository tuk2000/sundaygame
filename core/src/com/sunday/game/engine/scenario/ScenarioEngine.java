package com.sunday.game.engine.scenario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.AnimationTimer;
import com.sunday.game.engine.events.window.WindowEvent;
import com.sunday.game.engine.databank.DataBank;
import com.sunday.game.engine.databank.DataBankImpl;
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

    private DataBank dataBank;

    private boolean running;


    public ScenarioEngine() {
        dataBank = new DataBankImpl();
        Root = new Scenario(ScopeType.Game, dataBank);
        screenScenario = new Scenario(ScopeType.FullScreen, dataBank);
        Root.addKid(screenScenario);

        eventDispatcher = new EventDispatcher(Root);
        inputEventTransfer = new InputEventTransfer(eventDispatcher);
        collisionEventTransfer = new CollisionEventTransfer(eventDispatcher);
        Gdx.input.setInputProcessor(inputEventTransfer);
        Controllers.addListener(inputEventTransfer);

        physicSimulator = new PhysicSimulator(dataBank);
        physicSimulator.setContactListener(collisionEventTransfer);
        scenarioRenderer = new ScenarioRenderer(physicSimulator);
        eventDispatcher.addInternalEventProcessors(scenarioRenderer.getProcessors());
        scenarioAnalyser = new ScenarioAnalyser(scenarioRenderer, physicSimulator);

        AnimationTimer.initAnimationTimer(dataBank);
        running = true;
    }

    public Scenario constructScenario(ScopeType scopeType) {
        return new Scenario(scopeType, dataBank);
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
        eventDispatcher.dispatchEvent(WindowEvent.newResizeEvent(width, height));
    }
}
