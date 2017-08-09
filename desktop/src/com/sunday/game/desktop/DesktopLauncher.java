package com.sunday.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sunday.game.GameFramework.GameAdaptor;
import com.sunday.game.Graphic.TiledGameMap;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Sunday Game";
        config.useGL30 = true;
        config.width = 720;
        config.height = 800;
        config.resizable = false;

        new LwjglApplication(GameAdaptor.getInstance(), config);
        //new LwjglApplication(new TiledGameMap(), config);
    }
}
