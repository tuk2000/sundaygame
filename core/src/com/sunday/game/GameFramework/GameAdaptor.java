package com.sunday.game.GameFramework;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.sunday.game.GameFramework.GameFlow.GameFlowExecutor;
import com.sunday.game.GameFramework.Input.UserInputManager;
import com.sunday.tool.ToolApplication;

public class GameAdaptor extends Game implements GameFlowExecutor {
    private static GameAdaptor adaptorInstance;
    private Screen screenToSet;
    private UserInputManager userInputManager;

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
        Gdx.app.log("GameAdaptor", " GameAdaptor.create() was called by " + name);
    }

    @Override
    public void render() {
        //ClearColor White and it needs to be  defined only once before all render
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
        guardFrameworkInputProcessor();
    }


    public void guardInputManager(UserInputManager userInputManager) {
        this.userInputManager = userInputManager;
    }

    private void guardFrameworkInputProcessor() {
        if (userInputManager == null) return;
        if (Gdx.input.getInputProcessor() != userInputManager.getInputMultiplexer()) {
            Gdx.app.log("GameAdaptor", "The default InputProcessor has been changed and reversed ! ");
            userInputManager.setUserInputProcessor(Gdx.input.getInputProcessor());
            Gdx.input.setInputProcessor(userInputManager.getInputMultiplexer());
        }
    }
}
