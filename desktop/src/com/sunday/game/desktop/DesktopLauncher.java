package com.sunday.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sunday.game.GameEntry.GameAdaptor;
import com.sunday.game.GameEntry.GameFramework;
import com.sunday.game.Player.Player;
import com.sunday.game.World.GamePlay;
import com.sunday.game.World.Welcome;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Sunday Game";
        config.useGL30 = true;
        config.width = 1280;
        config.height = 720;
        config.resizable = false;

        new LwjglApplication(GameAdaptor.getInstance(), config);
        new GameFramework();
    }
}
