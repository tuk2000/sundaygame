package com.sunday.builder.simulation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class SimulationTask {
    private static int taskTag;

    static {
        taskTag++;
    }

    private ApplicationListener listener;
    private LwjglApplicationConfiguration config;

    public SimulationTask(ApplicationListener listener) {
        this.listener = listener;
        config = new LwjglApplicationConfiguration();
        config.title = "SimulationTask " + taskTag;
    }

    public SimulationTask(ApplicationListener listener, LwjglApplicationConfiguration config) {
        this.listener = listener;
        this.config = config;
        config.title = "SimulationTask " + taskTag + " : " + config.title;
    }

    public ApplicationListener getListener() {
        return listener;
    }

    public LwjglApplicationConfiguration getConfig() {
        return config;
    }
}
