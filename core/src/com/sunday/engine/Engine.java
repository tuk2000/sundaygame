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
import com.sunday.engine.event.window.WindowEventTransfer;
import com.sunday.engine.physic.PhysicSystem;
import com.sunday.engine.render.AnimationSetting;
import com.sunday.engine.render.AnimationTimer;
import com.sunday.engine.render.RenderSystem;
import com.sunday.engine.rule.RuleSystem;
import com.sunday.engine.scenario.ScenarioSystem;

public class Engine implements Disposable {
    private boolean running;
    private DataBank dataBank;
    private DriverSystem driverSystem;
    private EventSystem eventSystem;
    private RuleSystem ruleSystem;
    private ScenarioSystem scenarioSystem;
    private RenderSystem renderSystem;
    private PhysicSystem physicSystem;
    private WindowEventTransfer windowEventTransfer;

    public Engine() {
        dataBank = new DataBankImpl();
        driverSystem = new DriverSystem(dataBank.getSystemPort(DriverSystem.class));
        eventSystem = new EventSystem(dataBank.getSystemPort(EventSystem.class));
        ruleSystem = new RuleSystem(dataBank.getSystemPort(RuleSystem.class));
        scenarioSystem = new ScenarioSystem(dataBank.getSystemPort(ScenarioSystem.class));
        physicSystem = new PhysicSystem(dataBank.getSystemPort(PhysicSystem.class));
        renderSystem = new RenderSystem(dataBank.getSystemPort(PhysicSystem.class));
        renderSystem.setPhysicSystem(physicSystem);

        DriverEventTransfer driverEventTransfer = new DriverEventTransfer(driverSystem);
        Gdx.input.setInputProcessor(driverEventTransfer);
        Controllers.addListener(driverEventTransfer);

        CollisionEventTransfer collisionEventTransfer = new CollisionEventTransfer();
        windowEventTransfer = new WindowEventTransfer(eventSystem.getWindow());
        eventSystem.addEventTransfer(driverEventTransfer);
        eventSystem.addEventTransfer(collisionEventTransfer);
        eventSystem.addEventTransfer(windowEventTransfer);

        scenarioSystem.setRender(renderSystem);


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
        physicSystem.worldStep();
        renderSystem.render(delta);
    }

    public void resize(int width, int height) {
        windowEventTransfer.resize(width, height);
    }

    @Override
    public void dispose() {
        running = false;
        renderSystem.dispose();
        physicSystem.dispose();
        scenarioSystem.dispose();
        dataBank.dispose();
    }
}
