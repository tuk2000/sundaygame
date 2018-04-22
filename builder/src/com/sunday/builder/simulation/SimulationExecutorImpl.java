package com.sunday.builder.simulation;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import com.badlogic.gdx.backends.lwjgl.audio.OpenALAudio;

import javax.swing.*;
import java.awt.*;

public class SimulationExecutorImpl implements SimulationExecutor {

    public static Thread shutdownHook;
    private ApplicationListener listener;
    private LwjglApplicationConfiguration config;
    private Application application;
    private JPanel container;
    private boolean running;

    //code from LwjglFrame.setHaltOnShutdown
    public static void setHaltOnShutdown(boolean halt) {

        if (halt) {
            if (shutdownHook != null) return;
            shutdownHook = new Thread() {
                public void run() {
                    Runtime.getRuntime().halt(0);
                }
            };
            Runtime.getRuntime().addShutdownHook(shutdownHook);
        } else if (shutdownHook != null) {
            Runtime.getRuntime().removeShutdownHook(shutdownHook);
            shutdownHook = null;
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void setup(SimulationTask simulationTask, JPanel container) {
        this.listener = simulationTask.getListener();
        this.config = simulationTask.getConfig();
        this.container = container;

        container.setSize(config.width, config.height);
        container.setPreferredSize(new Dimension(config.width, config.height));
        container.setMaximumSize(new Dimension(1920, 1080));
        //container.setResizable(config.resizable);
        running = false;
        setHaltOnShutdown(true);
    }

    @Override
    public void execute() {
        container.removeAll();
        LwjglCanvas lwjglCanvas = new LwjglCanvas(listener, config) {
            @Override
            public void exit() {
                postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        listener.pause();
                        listener.dispose();
                        if (getAudio() != null) {
                            try {
                                OpenALAudio openALAudio = (OpenALAudio) getAudio();
                                openALAudio.dispose();
                            } catch (UnsatisfiedLinkError error) {
                                error.printStackTrace();
                            }
                        }
                        container.removeAll();
                        container.setVisible(false);
                        container.add(new JLabel(config.title + " was terminated !"));
                        container.setVisible(true);
                    }
                });
            }
        };
        application = lwjglCanvas;
        SwingUtilities.invokeLater(() -> {
            container.add(lwjglCanvas.getCanvas());
            container.setVisible(false);
            container.setVisible(true);
            container.requestFocus();
            running = true;
        });
    }

    @Override
    public void terminate() {
        if (running) {
            application.exit();
            container.removeAll();
            running = false;
        }
    }
}
