package com.sunday.game.GameFramework;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sunday.game.GameFramework.GameFlow.GameFlowExecutor;

public class GameHub extends Game implements GameFlowExecutor {
    private SpriteBatch batch;
    private BitmapFont font;
    private float duration;
    private long memoryUsage;
    private boolean isToDestroyed;
    private FocusedScreen focusedScreenToSet;

    @Override
    public void create() {
        duration = 1.0f;
        isToDestroyed = false;
        focusedScreenToSet = null;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(1, 0, 0, 1);
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

        //render the currentScreen
        super.render();
        //render additional information at tops layer of all
        batch.begin();
        font.draw(batch, "Info", 0, 720);
        font.draw(batch, "DeltaTime : " + Gdx.graphics.getDeltaTime(), 0, 700);
        font.draw(batch, "FramePerSecond : " + Gdx.graphics.getFramesPerSecond(), 0, 680);
        font.draw(batch, "FrameId : " + Gdx.graphics.getFrameId(), 0, 660);
        duration += Gdx.graphics.getDeltaTime();
        if (!isToDestroyed & duration > 1.0) {
            memoryUsage = (Gdx.app.getJavaHeap()) / 1024;
            duration = 0;
        }
        font.draw(batch, "MemoryUsage : " + memoryUsage + " KB", 0, 640);
        font.draw(batch, "press Esc -> go to GameIntro ", 0, 620);
        font.draw(batch, "press BackSpace -> turn back to last screen ", 0, 600);
        font.draw(batch, "press P -> go to GamePause , when it is supported", 0, 580);
        batch.end();

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