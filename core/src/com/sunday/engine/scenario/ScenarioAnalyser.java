package com.sunday.engine.scenario;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.physic.PhysicSimulator;
import com.sunday.engine.render.ScenarioRenderer;

public class ScenarioAnalyser implements Disposable {
    private ScenarioRenderer scenarioRenderer;
    private PhysicSimulator physicSimulator;

    public ScenarioAnalyser(ScenarioRenderer scenarioRenderer, PhysicSimulator physicSimulator) {
        this.scenarioRenderer = scenarioRenderer;
        this.physicSimulator = physicSimulator;
    }

    public void analyse(Scenario scenario) {
        scenario.getRoles().forEach(e -> {
            scenarioRenderer.readyToRenderRole(e);
        });
    }

    @Override
    public void dispose() {

    }
}
