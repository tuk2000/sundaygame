package com.sunday.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.databank.DataBank;
import com.sunday.engine.databank.DataBankImpl;
import com.sunday.engine.driver.DriverSystem;
import com.sunday.engine.event.EventSystem;
import com.sunday.engine.event.collision.CollisionEventTransfer;
import com.sunday.engine.event.driver.DriverEventTransfer;
import com.sunday.engine.event.window.WindowEvent;
import com.sunday.engine.physic.PhysicSimulator;
import com.sunday.engine.render.AnimationSetting;
import com.sunday.engine.render.AnimationTimer;
import com.sunday.engine.render.ScenarioRenderer;
import com.sunday.engine.rule.RuleSystem;
import com.sunday.engine.scenario.ScenarioSystem;

public class Engine implements Disposable {
    private boolean running;
    private DataBank dataBank;
    private DriverSystem driverSystem;
    private EventSystem eventSystem;
    private RuleSystem ruleSystem;
    private ScenarioSystem scenarioSystem;
    private ScenarioRenderer scenarioRenderer;
    private PhysicSimulator physicSimulator;

    public Engine() {
        dataBank = new DataBankImpl();
        driverSystem = new DriverSystem(dataBank.getSystemPort(DriverSystem.class));
        eventSystem = new EventSystem(dataBank.getSystemPort(EventSystem.class));
        ruleSystem = new RuleSystem(dataBank.getSystemPort(RuleSystem.class));
        scenarioSystem = new ScenarioSystem(dataBank.getSystemPort(ScenarioSystem.class));
        physicSimulator = new PhysicSimulator(dataBank.getSystemPort(PhysicSimulator.class));
        scenarioRenderer = new ScenarioRenderer(physicSimulator);

        DriverEventTransfer driverEventTransfer = new DriverEventTransfer(driverSystem);
        Gdx.input.setInputProcessor(driverEventTransfer);
        Controllers.addListener(driverEventTransfer);

        CollisionEventTransfer collisionEventTransfer = new CollisionEventTransfer();
        eventSystem.addEventTransfer(driverEventTransfer);
        eventSystem.addEventTransfer(collisionEventTransfer);

        scenarioSystem.setRender(scenarioRenderer);

        AnimationTimer.initAnimationTimer(dataBank);
        running = true;
    }

    public ScenarioSystem getScenarioSystem() {
        return scenarioSystem;
    }

    public RuleSystem getRuleSystem() {
        return ruleSystem;
    }

    public DataBank getDataBank() {
        return dataBank;
    }

    public void render(float delta) {
        if (!running) return;
        AnimationSetting.DeltaTime += delta;
        AnimationTimer.synchronize();
        scenarioSystem.analyse();
        physicSimulator.worldStep();
        scenarioRenderer.render(delta);
    }

    public void resize(int width, int height) {
        eventSystem.dispatchEvent(WindowEvent.newResizeEvent(width, height));
    }

    @Override
    public void dispose() {
        running = false;
        scenarioRenderer.dispose();
        physicSimulator.dispose();
        scenarioSystem.dispose();
    }
}
