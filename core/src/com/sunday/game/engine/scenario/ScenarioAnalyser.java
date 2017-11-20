package com.sunday.game.engine.scenario;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.scenario.physicprocess.PhysicSimulator;
import com.sunday.game.engine.scenario.render.ScenarioRenderer;

public class ScenarioAnalyser implements Disposable {
    private ScenarioRenderer scenarioRenderer;
    private PhysicSimulator physicSimulator;

    public ScenarioAnalyser(ScenarioRenderer scenarioRenderer, PhysicSimulator physicSimulator) {
        this.scenarioRenderer = scenarioRenderer;
        this.physicSimulator = physicSimulator;
    }

    public void analyse(Scenario scenario) {
        scenario.getRoles().forEach(scenarioRenderer::readyToRenderRole);
    }

    @Override
    public void dispose() {

    }
}
