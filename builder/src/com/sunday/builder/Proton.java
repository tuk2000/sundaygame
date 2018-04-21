package com.sunday.builder;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sunday.game.framework.GameAdaptor;

public class Proton {


    public static void main(String[] args) {
        // EngineContainer engineContainer = new EngineContainer();

        MainWindow mainWindow = new MainWindow();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Proton-Builder";
        config.useGL30 = false;
        config.width = 1080;
        config.height = 720;
        config.resizable = true;

        LwjglAWTCanvas lwjglAWTCanvas = new LwjglAWTCanvas(GameAdaptor.getInstance(), config);
        mainWindow.getContentPane().add(lwjglAWTCanvas.getCanvas());
    }
}
