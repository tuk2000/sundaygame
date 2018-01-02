package com.sunday.game.framework.gameflow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.sunday.tool.ToolApplication;


public final class GameFlowManager {
    private ScreenGenerator screenGenerator;
    private GameFlowExecutor gameFlowExecutor;

    public GameFlowManager(ScreenGenerator screenGenerator, GameFlowExecutor gameFlowExecutor) {
        this.screenGenerator = screenGenerator;
        this.gameFlowExecutor = gameFlowExecutor;
    }

    private void applyScreen(Screen Screen) {
        gameFlowExecutor.setScreen(Screen);
    }

    private void applyNewScreen(GameStatus status) {
        Screen screen = screenGenerator.generateScreen(status);
        if (status == GameStatus.Loading) {
            GameFlow.setFirstGameFlow(status, screen);
        } else {
            GameFlow.appendGameFlow(status, screen);
        }
        Gdx.app.log("GameFlowManager", "GameStatus changed to " + status.name());
        ToolApplication.objectMonitor.MonitorObject(screen);
        applyScreen(screen);
    }

    private void shiftBackPreviewScreen() {
        if (GameFlow.getCurrentGameStatus() == GameStatus.Loading)
            return;
        if (GameFlow.getCurrentGameStatus() == GameStatus.Intro) {
            replaceIntroScreen();
        } else {
            Screen screen = GameFlow.getCurrentScreen();
            Gdx.app.log("GameFlowManager", GameFlow.getCurrentGameStatus().name() + " closed");
            ToolApplication.objectMonitor.StopMonitorObject(screen);
            System.gc();

            GameFlow.backToPreviewGameFlow();
            Gdx.app.log("GameFlowManager", GameFlow.getCurrentGameStatus().name() + " opened");
            if (GameFlow.getCurrentGameStatus() == GameStatus.Intro) {
                replaceIntroScreen();
            } else {
                screen = GameFlow.getCurrentScreen();
                applyScreen(screen);
            }
        }
    }

    private void replaceIntroScreen() {
        if (GameFlow.getCurrentGameStatus() != GameStatus.Intro)
            return;
        Screen screen = GameFlow.getCurrentScreen();
        ToolApplication.objectMonitor.StopMonitorObject(screen);
        System.gc();
        screen = screenGenerator.generateScreen(GameStatus.Intro);
        Gdx.app.log("GameFlowManager", "IntroScreen replaced");
        ToolApplication.objectMonitor.MonitorObject(screen);
        GameFlow.setCurrentScreen(screen);
        applyScreen(screen);
    }


    /**
     * Called when it needs to change status of the game
     */
    public final synchronized void setGameStatus(final GameStatus gameStatus) {
        /*
         *this should be executed asynchronous ,otherwise it may dispose the original caller (such as InGame)  and
         * lead to program crash
         */
        Gdx.app.postRunnable(() -> {
            executeGameStatus(gameStatus);
        });
    }

    public final synchronized void backToPreviewStatus() {
        /*
         *this should be executed asynchronous ,otherwise it may dispose the original caller (such as InGame)  and
         * lead to program crash
         */
        Gdx.app.postRunnable(() -> {
            shiftBackPreviewScreen();
        });
    }

    private void executeGameStatus(GameStatus gameStatus) {
        Screen screen;
        switch (gameStatus) {
            case Loading:
                /* this will be executed at first the program runs */
                applyNewScreen(GameStatus.Loading);
                break;
            case Intro:
                /* this will be executed  when Esc pressed  or after Loading*/
                switch (GameFlow.getCurrentGameStatus()) {
                    case Loading:
                        applyNewScreen(GameStatus.Intro);
                        break;
                    case Intro:
                        replaceIntroScreen();
                        break;
                    default:
                        while (GameFlow.getCurrentGameStatus() != GameStatus.Intro) {
                            shiftBackPreviewScreen();
                        }
                        replaceIntroScreen();
                }
                break;
            case Setting:
            case InGame:
            case GamePause:
            case Test:
                /*
                Intro : the MainMenu screen
                Setting : add new Setting screen at MainMenu
                InGame: add new game screen
                GamePause: add new GamePause screen
                Test : add new Test screen at MainMenu
                */
                applyNewScreen(gameStatus);
                break;
            case MapOfGame:
                /*
                Intro : the MainMenu screen
                Setting : add new Setting screen at MainMenu
                InGame: add new game screen
                GamePause: add new GamePause screen
                Test : add new Test screen at MainMenu
                */
                applyNewScreen(gameStatus);
                break;
            case WorldShiftStart:
            case WorldShiftEnd:
                /*
                WorldShiftStart: clear the old Game ,shift not into intro but instead into an animations
                WorldShiftEnd: clear the animations ,shift into new game screen
                */
                applyNewScreen(gameStatus);
                break;
//            case BackToIntro:
//                /*
//                return to GameIntro , used only when the current status is InGame ,Test or Setting
//                that means there muss be only two Screen in ScreenStack before backToPreviewStatus();
//                if less than 2 ,(which is 1) do nothing
//                */
//
//                shiftToNextScreen(gameStatus);
//
//                break;
            case Exit:
                /* clean all saved screens */
                Gdx.app.exit();
                break;
        }

    }
}
