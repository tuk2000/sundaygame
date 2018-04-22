package com.sunday.builder.simulation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import com.badlogic.gdx.backends.lwjgl.audio.OpenALAudio;

import javax.swing.*;
import java.awt.*;

public class SimulationWindow extends JInternalFrame {
    public static Thread shutdownHook;

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

    private ApplicationListener listener;
    private LwjglCanvas lwjglCanvas;
    private LwjglApplicationConfiguration config;

    public SimulationWindow(ApplicationListener listener) {
        config = new LwjglApplicationConfiguration();
        config.width = 1280;
        config.height = 720;
        config.forceExit = false;
        this.listener = listener;
        initialize();
    }

    public SimulationWindow(ApplicationListener listener, LwjglApplicationConfiguration config) {
        this.config = config;
        config.forceExit = false;
        this.listener = listener;
        initialize();
    }

    private void initialize() {
        lwjglCanvas = new LwjglCanvas(listener, config) {
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
                        SimulationWindow.this.dispose();
                    }
                });
            }
        };
        setTitle("Simulation : " + config.title);
        setSize(config.width, config.height);
        setPreferredSize(new Dimension(config.width, config.height));
        setMaximumSize(new Dimension(1920, 1080));
        setResizable(config.resizable);
        SwingUtilities.invokeLater(() -> {
            System.out.println(Thread.currentThread());
            getContentPane().add(lwjglCanvas.getCanvas());
            setVisible(true);
            getContentPane().requestFocus();
        });
        setHaltOnShutdown(true);
    }
}
