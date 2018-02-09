package com.sunday.game.framework.gameflow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.sunday.game.framework.display.ScreenDisplay;
import com.sunday.game.framework.display.ScreenGenerator;
import com.sunday.tool.ToolApplication;


public final class GameFlowManager {
    private ScreenGenerator screenGenerator;
    private ScreenDisplay screenDisplay;

    private GameFlow gameFlow;

    public GameFlowManager(ScreenGenerator screenGenerator, ScreenDisplay screenDisplay) {
        this.screenGenerator = screenGenerator;
        this.screenDisplay = screenDisplay;
        gameFlow = new GameFlow(screenGenerator.getScreenClasses());
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
        Class<? extends Screen> screenClass = gameFlow.getScreenClass(screenClassName);
        Screen screen = screenGenerator.generateScreen(screenClass);
        gameFlow.appendScreen(screen);
        screenDisplay.setScreen(screen);

        Gdx.app.log("GameFlowManager", screen.getClass().getCanonicalName() + " opened");
        ToolApplication.dataMonitor.MonitorObject(screen);
    }

    public final synchronized void backToPreviewScreen() {
        /*
         *this should be executed asynchronous ,otherwise it may dispose the original caller (such as InGame)  and
         * lead to program crash
         */
        Gdx.app.postRunnable(() -> {
            switch (gameFlow.normalScreenAmount()) {
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
        Screen screen = gameFlow.popCurrentScreen();
        screenDisplay.setScreen(gameFlow.current());

        Gdx.app.log("GameFlowManager", screen.getClass().getCanonicalName() + " closed");
        ToolApplication.dataMonitor.StopMonitorObject(screen);
        System.gc();
    }

    public void gotoLoadingScreen() {
        if (gameFlow.getLoadingScreen() == null) {
            gameFlow.setLoadingScreen(screenGenerator.generateLoadingScreen());
            screenDisplay.setScreen(gameFlow.getLoadingScreen());
            Gdx.app.log("GameFlowManager", gameFlow.getLoadingScreen().getClass().getCanonicalName() + " opened");
            ToolApplication.dataMonitor.MonitorObject(gameFlow.getLoadingScreen());
        }
    }

    public void gotoIntoScreen() {
        if (gameFlow.getIntroScreen() == null) {
            gameFlow.setIntroScreen(screenGenerator.generateIntroScreen());
            Gdx.app.log("GameFlowManager", gameFlow.getIntroScreen().getClass().getCanonicalName() + " opened");
            ToolApplication.dataMonitor.MonitorObject(gameFlow.getIntroScreen());
        } else {
            while (gameFlow.normalScreenAmount() != 0) {
                Screen screen = gameFlow.popCurrentScreen();
                Gdx.app.log("GameFlowManager", screen.getClass().getCanonicalName() + " closed");
                ToolApplication.dataMonitor.StopMonitorObject(screen);
            }
            System.gc();
        }
        screenDisplay.setScreen(gameFlow.getIntroScreen());
        Gdx.app.log("GameFlowManager", gameFlow.getIntroScreen().getClass().getCanonicalName() + " reopened");
    }
}
