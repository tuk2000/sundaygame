package com.sunday.game.GameFramework;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;

import java.util.concurrent.atomic.AtomicBoolean;

public class GameAdaptor implements ApplicationListener {
    private static GameAdaptor adaptorInstance;
    private ApplicationListener currentListener = null;
    private AtomicBoolean secureTransmit = new AtomicBoolean(false);

    private GameAdaptor() {

    }

    public static final synchronized GameAdaptor getInstance() {
        if (adaptorInstance == null) {
            adaptorInstance = new GameAdaptor();
        }
        return adaptorInstance;
    }

    public final ApplicationListener getCurrentListener() {
        return currentListener;
    }

    /**
     * Called when it needs to change the currentListener to specific game
     */
    public final synchronized void setCurrentListener(ApplicationListener applicationListener) {
        secureTransmit.set(false);
        ApplicationListener old = this.currentListener;
        if (old == null || old != applicationListener) {
            this.currentListener = applicationListener;
            this.currentListener.create();
        }
        secureTransmit.set(true);
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

    /**
     * Called when the {@link Application} is resized. This can happen at any point during a non-paused state but will never happen
     * before a call to {@link #create()}.
     *
     * @param width  the new width in pixels
     * @param height the new height in pixels
     */
    @Override
    public void resize(int width, int height) {
        if (secureTransmit.get()) currentListener.resize(width, height);
    }

    /**
     * Called when the {@link Application} should render itself.
     */
    @Override
    public void render() {
        if (secureTransmit.get()) currentListener.render();
    }

    /**
     * Called when the {@link Application} is paused, usually when it's not active or visible on screen. An Application is also
     * paused before it is destroyed.
     */
    @Override
    public void pause() {
        if (secureTransmit.get()) currentListener.pause();
    }

    /**
     * Called when the {@link Application} is resumed from a paused state, usually when it regains focus.
     */
    @Override
    public void resume() {
        if (secureTransmit.get()) currentListener.resume();
    }

    /**
     * Called when the {@link Application} is destroyed. Preceded by a call to {@link #pause()}.
     */
    @Override
    public void dispose() {
        if (secureTransmit.get()) currentListener.resume();
    }


}
