package com.sunday.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sunday.game.SundayMain;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Sunday Game";
        config.useGL30 = true;
        config.width = 480;
        config.height = 320;

        new LwjglApplication(new SundayMain(), config);
    }
}
