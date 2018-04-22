package com.sunday.builder;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sunday.builder.simulation.SimulationTask;
import com.sunday.game.framework.GameAdaptor;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class Proton {
    static {
        setDefaultFontSize(25);
    }

    public static void setDefaultFontSize(int size) {
        Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
        Object[] keys = keySet.toArray(new Object[keySet.size()]);
        for (Object key : keys) {
            if (key != null && key.toString().toLowerCase().contains("font")) {
                Font font = UIManager.getDefaults().getFont(key);
                if (font != null) {
                    font = font.deriveFont((float) size);
                    UIManager.put(key, font);
                }
            }
        }
    }

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
