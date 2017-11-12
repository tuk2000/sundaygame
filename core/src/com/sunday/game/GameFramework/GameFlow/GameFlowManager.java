package com.sunday.game.GameFramework.GameFlow;

import com.badlogic.gdx.Gdx;
import com.sunday.game.GameFramework.FocusedScreen;
import com.sunday.game.GameFramework.GameFramework;
import com.sunday.tool.ToolApplication;


public final class GameFlowManager {
    private FocusedScreenGenerator focusedScreenGenerator;
    private GameFlowExecutor gameFlowExecutor;

    public GameFlowManager(FocusedScreenGenerator focusedScreenGenerator, GameFlowExecutor gameFlowExecutor) {
        this.focusedScreenGenerator = focusedScreenGenerator;
        this.gameFlowExecutor = gameFlowExecutor;
    }

    private void applyFocusedScreen(FocusedScreen focusedScreen) {
        gameFlowExecutor.setCurrentFocusedScreen(focusedScreen);
        GameFramework.InputProcessor.setInputReceiver(focusedScreen);
    }

    private void applyNewScreen(GameStatus status) {
        FocusedScreen focusedScreen = focusedScreenGenerator.generateFocusedScreen(status);
        if (status == GameStatus.Loading) {
            GameFlow.setFirstGameFlow(status, focusedScreen);
        } else {
            GameFlow.appendGameFlow(status, focusedScreen);
        }
        GameFramework.app.log("GameFlowManager", "GameStatus changed to " + status.name());
        ToolApplication.objectMonitor.MonitorObject(focusedScreen);
        applyFocusedScreen(focusedScreen);
    }

    private void shiftBackPreviewScreen() {
        if (GameFlow.getCurrentGameStatus() == GameStatus.Loading)
            return;
        if (GameFlow.getCurrentGameStatus() == GameStatus.Intro) {
            replaceIntroScreen();
        } else {
            FocusedScreen focusedScreen = GameFlow.getCurrentScreen();
            GameFramework.app.log("GameFlowManager", GameFlow.getCurrentGameStatus().name() + " closed");
            ToolApplication.objectMonitor.StopMonitorObject(focusedScreen);
            System.gc();

            GameFlow.backToPreviewGameFlow();
            GameFramework.app.log("GameFlowManager", GameFlow.getCurrentGameStatus().name() + " opened");
            if (GameFlow.getCurrentGameStatus() == GameStatus.Intro) {
                replaceIntroScreen();
            } else {
                focusedScreen = GameFlow.getCurrentScreen();
                applyFocusedScreen(focusedScreen);
            }
        }
    }

    private void replaceIntroScreen() {
        if (GameFlow.getCurrentGameStatus() != GameStatus.Intro)
            return;
        FocusedScreen focusedScreen = GameFlow.getCurrentScreen();
        ToolApplication.objectMonitor.StopMonitorObject(focusedScreen);
        System.gc();
        focusedScreen = focusedScreenGenerator.generateFocusedScreen(GameStatus.Intro);
        GameFramework.app.log("GameFlowManager", "IntroScreen replaced");
        ToolApplication.objectMonitor.MonitorObject(focusedScreen);
        GameFlow.setCurrentScreen(focusedScreen);
        applyFocusedScreen(focusedScreen);
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
        FocusedScreen focusedScreen;
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
                WorldShiftStart: clear the old Game ,shift not into intro but instead into an Animation
                WorldShiftEnd: clear the Animation ,shift into new game screen
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
//                shiftToNextFocusedScreen(gameStatus);
//
//                break;
            case Exit:
                /* clean all saved screens */
                Gdx.app.exit();
                break;
        }

    }
}
