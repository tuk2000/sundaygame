package com.sunday.game.GameFramework;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;


import java.util.concurrent.atomic.AtomicBoolean;

public class GameAdaptor implements ApplicationListener {
    private static GameAdaptor adaptorInstance;
    private static ApplicationListener currentListener = null;
    private static AtomicBoolean secureTransmit = new AtomicBoolean(false);

    private GameAdaptor() {

    }

    public final ApplicationListener getCurrentListener() {
        return currentListener;
    }

    public static synchronized void setCurrentListener(ApplicationListener currentListener) {
        System.out.println(Thread.currentThread().toString());
        String name = Thread.currentThread().getStackTrace()[3].getClassName();
        System.out.println(System.nanoTime() + " GameAdaptor.setCurrentListener() was called by " + name);
        secureTransmit.set(false);
        ApplicationListener old = GameAdaptor.currentListener;
        GameAdaptor.currentListener = currentListener;
        if (old == null || old != currentListener) {
            currentListener.create();
        }
        secureTransmit.set(true);
    }

    public static final synchronized GameAdaptor getInstance() {
        if (adaptorInstance == null) {
            adaptorInstance = new GameAdaptor();
        }
        return adaptorInstance;
    }


    /**
     * Called when the {@link Application} is first created.
     */
    @Override
    public void create() {
        new GameFramework();
        System.out.println(Thread.currentThread().toString());
        String name = Thread.currentThread().getStackTrace()[3].getClassName();
        System.out.println(System.nanoTime() + " GameAdaptor.create() was called by " + name);
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