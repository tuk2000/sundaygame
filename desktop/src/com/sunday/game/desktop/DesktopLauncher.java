package com.sunday.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sunday.game.Game;
import com.sunday.game.Player;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Sunday Game";
        //config.useGL30 = true;
        config.width = 1040;
        config.height = 600;
        //config.resizable = false;

        new LwjglApplication(new Player(), config);
    }
}
