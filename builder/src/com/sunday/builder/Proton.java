package com.sunday.builder;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sunday.builder.simulation.SimulationTask;
import com.sunday.game.framework.GameAdaptor;

public class Proton {


    public static void main(String[] args) {
        // EngineContainer engineContainer = new EngineContainer();

        BuilderWindow builderWindow = new BuilderWindow();

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Sunday Game";
        config.useGL30 = false;
        config.width = 1080;
        config.height = 720;
        config.resizable = true;

        SimulationTask simulationTask = new SimulationTask(GameAdaptor.getInstance(), config);
        builderWindow.setDemoSimulationTask(simulationTask);

    }
}
