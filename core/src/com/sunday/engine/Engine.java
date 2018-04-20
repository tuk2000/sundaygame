package com.sunday.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.contextbank.ContextBank;
import com.sunday.engine.contextbank.ContextBankImpl;
import com.sunday.engine.databank.DataBank;
import com.sunday.engine.databank.DataBankImpl;
import com.sunday.engine.environment.driver.DriverEnvironment;
import com.sunday.engine.environment.driver.DriverSystem;
import com.sunday.engine.environment.time.TimeSystem;
import com.sunday.engine.environment.window.WindowEnvironment;
import com.sunday.engine.physic.CollisionListener;
import com.sunday.engine.physic.PhysicSystem;
import com.sunday.engine.render.RenderSystem;
import com.sunday.engine.rule.RuleSystem;
import com.sunday.engine.scenario.ScenarioSystem;

public class Engine implements Disposable {
    private boolean running;
    private ContextBank contextBank;
    private DataBank dataBank;
    private RuleSystem ruleSystem;
    private DriverSystem driverSystem;

    private TimeSystem timeSystem;
    private ScenarioSystem scenarioSystem;
    private RenderSystem renderSystem;
    private PhysicSystem physicSystem;
    private WindowEnvironment windowEnvironment;

    public Engine() {
        dataBank = new DataBankImpl();
        ContextBankImpl contextBankImpl = new ContextBankImpl();
        contextBank = contextBankImpl;
        ruleSystem = new RuleSystem(dataBank.getSystemPort(RuleSystem.class), contextBank);

        driverSystem = new DriverSystem(dataBank.getSystemPort(DriverSystem.class), contextBank);
        ruleSystem.addDataProvider(driverSystem);
        driverSystem.connectToDriverMonitor();

        timeSystem = new TimeSystem(dataBank.getSystemPort(TimeSystem.class), contextBank);
        ruleSystem.addDataProvider(timeSystem);

        windowEnvironment = new WindowEnvironment(contextBank);
        ruleSystem.addDataProvider(windowEnvironment);

        physicSystem = new PhysicSystem(dataBank.getSystemPort(PhysicSystem.class), contextBank);
        physicSystem.setContactListener(new CollisionListener(contextBank));
        ruleSystem.addDataProvider(physicSystem);

        renderSystem = new RenderSystem(dataBank.getSystemPort(PhysicSystem.class));
        renderSystem.setPhysicSystem(physicSystem);

        scenarioSystem = new ScenarioSystem(dataBank.getSystemPort(ScenarioSystem.class));
        scenarioSystem.setRender(renderSystem);

        DriverEnvironment driverEnvironment = new DriverEnvironment(driverSystem);
        Gdx.input.setInputProcessor(driverEnvironment);
        for (Controller controller : Controllers.getControllers()) {
            driverEnvironment.connected(controller);
        }
        Controllers.addListener(driverEnvironment);

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
        timeSystem.updateTime(delta);
        scenarioSystem.analyse();
        physicSystem.worldStep();
        renderSystem.render(delta);
    }

    public void resize(int width, int height) {
        windowEnvironment.resize(width, height);
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
