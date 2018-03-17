package com.sunday.engine.scenario;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.physic.PhysicSystem;
import com.sunday.engine.render.RenderSystem;

public class ScenarioAnalyser implements Disposable {
    private RenderSystem renderSystem;
    private PhysicSystem physicSystem;

    public ScenarioAnalyser(RenderSystem renderSystem, PhysicSystem physicSystem) {
        this.renderSystem = renderSystem;
        this.physicSystem = physicSystem;
    }

    public void analyse(Scenario scenario) {
        scenario.getRoles().forEach(e -> {
            renderSystem.readyToRenderRole(e);
        });
    }

    @Override
    public void dispose() {

    }
}
