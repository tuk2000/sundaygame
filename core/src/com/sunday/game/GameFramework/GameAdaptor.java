package com.sunday.game.GameFramework;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.sunday.game.GameFramework.GameFlow.GameFlowExecutor;

public class GameAdaptor extends Game implements GameFlowExecutor {
    private static GameAdaptor adaptorInstance;
    private FocusedScreen focusedScreenToSet;
    private float duration = 0.0f;

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
     * the initialization of the GameFramework should done after Gdx is initialed , which will be done before ApplicationListener.create() was called
     */
    @Override
    public void create() {
        new GameFramework();
        String name = Thread.currentThread().getStackTrace()[3].getClassName();
        GameFramework.app.log("GameAdaptor", " GameAdaptor.create() was called by " + name);
    }

    @Override
    public void render() {
        //ClearColor White and it needs to be  defined only once before all render
        Gdx.gl.glClearColor(1, 1, 1, 1);
        //clear the screen before anything rendered
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (focusedScreenToSet != null) {
            setScreen(focusedScreenToSet);
            focusedScreenToSet = null;
        }

        super.render();
        duration += Gdx.graphics.getDeltaTime();
        if (duration > 0.10f) {
            GameFramework.Tool.gameMonitor.updateGameData();
            duration = 0.0f;
        }
    }

    @Override
    public FocusedScreen getCurrentFocusedScreen() {
        return (FocusedScreen) screen;
    }

    /*  setCurrentFocusedScreen should only be called by GameFlowManager , in oder to change game status and change FocusedScreen*/
    @Override
    public void setCurrentFocusedScreen(FocusedScreen currentFocusedScreen) {
        focusedScreenToSet = currentFocusedScreen;
    }

}
