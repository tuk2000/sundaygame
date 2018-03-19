package com.sunday.engine.scenario;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.SubSystem;
import com.sunday.engine.databank.Port;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.render.RenderSystem;

public class ScenarioSystem extends SubSystem implements Disposable {
    private Scenario root;
    private RenderSystem render;

    public ScenarioSystem(SystemPort systemPort) {
        super("ScenarioSystem", systemPort);
        root = new Scenario(ScopeType.Game);
        systemPort.addDataInstance(root);
    }

    public Scenario getRootScenario() {
        return root;
    }

    public void setRoot(Scenario root) {
        systemPort.deleteDataInstance(this.root);
        this.root = root;
        init(root);
    }

    private void init(Scenario scenario) {
        systemPort.addDataInstance(scenario);
        scenario.getRoles().forEach(role -> {
            Port port = systemPort.requestPort(role);
            role.abstractModel.connectWith(port);
        });
        scenario.getKids().forEach(this::init);
    }

    public void analyse() {
        root.getRoles().forEach(e -> {
            render.readyToRenderRole(e);
        });
    }

    private void dispose(Scenario scenario) {
        systemPort.deleteDataInstance(scenario);
        scenario.getRoles().forEach(role -> {
            Port port = systemPort.requestPort(role);
            role.abstractModel.disconnectWith(port);
            role.abstractModel.dispose();
        });
        scenario.getKids().forEach(this::dispose);
    }

    @Override
    public void dispose() {
        dispose(root);
    }

    public void setRender(RenderSystem render) {
        this.render = render;
    }
}
