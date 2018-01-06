package com.sunday.game.framework;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.sunday.game.framework.display.ScreenDisplay;
import com.sunday.tool.ToolApplication;

public class GameAdaptor extends Game implements ScreenDisplay {
    private static GameAdaptor adaptorInstance;
    private Screen screenToSet;

    private GameAdaptor() {

    }

    public static final synchronized GameAdaptor getInstance() {
        if (adaptorInstance == null) {
            adaptorInstance = new GameAdaptor();
        }
        return adaptorInstance;
    }

    /**
     * Called when the {@link Application} is first created.
     * It will be called only once by LwjglApplication
     * the initialization of the framework should done after Gdx is initialed , which will be done before ApplicationListener.create() was called
     */
    @Override
    public void create() {
        new GameFramework();
        String name = Thread.currentThread().getStackTrace()[3].getClassName();
        Gdx.app.log("GameAdaptor", " GameAdaptor.create() was called by " + name);
    }

    @Override
    public void render() {
        //ClearColor White and it needs to be  defined only once before all render
        Gdx.gl.glClearColor(0.745f, 0.745f, 0.745f, 1);
        //clear the screen before anything rendered
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (screenToSet != null) {
            setScreen(screenToSet);
            screenToSet = null;
        }

        super.render();
        float delta = Gdx.graphics.getDeltaTime();
        long memoryUsage = Gdx.app.getJavaHeap();
        int fps = Gdx.graphics.getFramesPerSecond();
        ToolApplication.gameMonitor.updateData(delta, memoryUsage, fps);
    }
}
