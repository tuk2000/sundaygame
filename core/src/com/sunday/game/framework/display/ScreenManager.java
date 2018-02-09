package com.sunday.game.framework.display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.sunday.tool.ToolApplication;


public final class ScreenManager {
    private ScreenGenerator screenGenerator;
    private ScreenDisplay screenDisplay;

    private ScreenHistory screenHistory;

    public ScreenManager(ScreenGenerator screenGenerator, ScreenDisplay screenDisplay) {
        this.screenGenerator = screenGenerator;
        this.screenDisplay = screenDisplay;
        screenHistory = new ScreenHistory(screenGenerator.getScreenClasses());
    }


    /**
     * Called when it needs to change screen of the game
     */
    public final synchronized void setCurrentScreen(String screenClassName) {
        /*
         *this should be executed asynchronous ,otherwise it may dispose the original caller (such as InGame)  and
         * lead to program crash
         */
        Gdx.app.postRunnable(() -> {
            setCurrentScreenInternal(screenClassName);
        });
    }

    /**
     * Called when it needs to change screen of the game
     */
    public final synchronized void setCurrentScreen(Class<? extends Screen> screenClass) {
        /*
         *this should be executed asynchronous ,otherwise it may dispose the original caller (such as InGame)  and
         * lead to program crash
         */
        Gdx.app.postRunnable(() -> {
            setCurrentScreenInternal(screenClass.getCanonicalName());
        });
    }

    private void setCurrentScreenInternal(String screenClassName) {
        Class<? extends Screen> screenClass = screenHistory.getScreenClass(screenClassName);
        Screen screen = screenGenerator.generateScreen(screenClass);
        screenHistory.appendScreen(screen);
        screenDisplay.setScreen(screen);

        Gdx.app.log("ScreenManager", screen.getClass().getCanonicalName() + " opened");
        ToolApplication.dataMonitor.MonitorObject(screen);
    }

    public final synchronized void backToPreviewScreen() {
        /*
         *this should be executed asynchronous ,otherwise it may dispose the original caller (such as InGame)  and
         * lead to program crash
         */
        Gdx.app.postRunnable(() -> {
            switch (screenHistory.normalScreenAmount()) {
                case 0:
                case 1:
                    gotoIntoScreen();
                    break;
                default:
                    popCurrentScreen();
            }
        });
    }

    private void popCurrentScreen() {
        Screen screen = screenHistory.popCurrentScreen();
        screenDisplay.setScreen(screenHistory.current());

        Gdx.app.log("ScreenManager", screen.getClass().getCanonicalName() + " closed");
        ToolApplication.dataMonitor.StopMonitorObject(screen);
        System.gc();
    }

    public void gotoLoadingScreen() {
        if (screenHistory.getLoadingScreen() == null) {
            screenHistory.setLoadingScreen(screenGenerator.generateLoadingScreen());
            screenDisplay.setScreen(screenHistory.getLoadingScreen());
            Gdx.app.log("ScreenManager", screenHistory.getLoadingScreen().getClass().getCanonicalName() + " opened");
            ToolApplication.dataMonitor.MonitorObject(screenHistory.getLoadingScreen());
        }
    }

    public void gotoIntoScreen() {
        if (screenHistory.getIntroScreen() == null) {
            screenHistory.setIntroScreen(screenGenerator.generateIntroScreen());
            Gdx.app.log("ScreenManager", screenHistory.getIntroScreen().getClass().getCanonicalName() + " opened");
            ToolApplication.dataMonitor.MonitorObject(screenHistory.getIntroScreen());
        } else {
            while (screenHistory.normalScreenAmount() != 0) {
                Screen screen = screenHistory.popCurrentScreen();
                Gdx.app.log("ScreenManager", screen.getClass().getCanonicalName() + " closed");
                ToolApplication.dataMonitor.StopMonitorObject(screen);
            }
            System.gc();
        }
        screenDisplay.setScreen(screenHistory.getIntroScreen());
        Gdx.app.log("ScreenManager", screenHistory.getIntroScreen().getClass().getCanonicalName() + " reopened");
    }
}
