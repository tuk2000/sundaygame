package com.sunday.engine.scenario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.AnimationTimer;
import com.sunday.engine.databank.DataBank;
import com.sunday.engine.databank.DataBankImpl;
import com.sunday.engine.driver.DriverSystem;
import com.sunday.engine.event.driver.DriverEventTransfer;
import com.sunday.engine.event.synchronize.CollisionEventTransfer;
import com.sunday.engine.event.window.WindowEvent;
import com.sunday.engine.scenario.eventpocess.EventDispatcher;
import com.sunday.engine.scenario.physicprocess.PhysicSimulator;
import com.sunday.engine.scenario.render.ScenarioRenderer;

public class ScenarioEngine implements Disposable {
    private Scenario Root;
    private Scenario screenScenario;
    private EventDispatcher eventDispatcher;
    private DriverSystem driverSystem;
    private DriverEventTransfer driverEventTransfer;
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
        driverSystem = new DriverSystem(dataBank.getSystemPort(DriverSystem.class));
        driverEventTransfer = new DriverEventTransfer(driverSystem);
        collisionEventTransfer = new CollisionEventTransfer();
        Gdx.input.setInputProcessor(driverEventTransfer);
        Controllers.addListener(driverEventTransfer);

        physicSimulator = new PhysicSimulator(dataBank.getSystemPort(PhysicSimulator.class));
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
