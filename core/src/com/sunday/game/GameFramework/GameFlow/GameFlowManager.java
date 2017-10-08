package com.sunday.game.GameFramework.GameFlow;

import com.badlogic.gdx.Gdx;
import com.sunday.game.GameFramework.FocusedScreen;
import com.sunday.game.GameFramework.GameFramework;

import java.util.Stack;

public class GameFlowManager {
    private FocusedScreenGenerator focusedScreenGenerator;
    private GameFlowExecutor gameFlowExecutor;
    private Stack<GameStatus> statusStack = new Stack<GameStatus>();
    private Stack<FocusedScreen> screenStack = new Stack<FocusedScreen>();

    public GameFlowManager(FocusedScreenGenerator focusedScreenGenerator, GameFlowExecutor gameFlowExecutor) {
        this.focusedScreenGenerator = focusedScreenGenerator;
        this.gameFlowExecutor = gameFlowExecutor;
    }

    /**
     * Called when it needs to change status of the game
     */
    public final synchronized void setGameStatus(final GameStatus gameStatus) {
        /*
        *this should be executed asynchronous ,otherwise it may dispose the original caller (such as InGame)  and
        * lead to program crash
        */
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
//                System.out.println("----------------------------------------------");
//                System.out.println(Thread.currentThread().toString() + " will excute GameStatus " + gameStatus.name());
                executeGameStatus(gameStatus);
//                System.out.println("----------------------------------------------");
            }
        });
    }

    private void executeGameStatus(GameStatus gameStatus) {
        FocusedScreen focusedScreen;
        switch (gameStatus) {
            case Loading:
                /* this will be executed at first the program runs */
                shiftToNextFocusedScreen(gameStatus);
                break;
            case Intro:
                /* this will be executed  when Esc pressed  or after Loading*/
                switch (statusStack.peek()) {
                    case Loading:
                        shiftToNextFocusedScreen(gameStatus);
                        break;
                    case Intro:
                    default:
                        disposeCurrentFocusScreen();
                        shiftToNextFocusedScreen(gameStatus);
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
                shiftToNextFocusedScreen(gameStatus);
                break;
            case MapOfGame:
                /*
                Intro : the MainMenu screen
                Setting : add new Setting screen at MainMenu
                InGame: add new game screen
                GamePause: add new GamePause screen
                Test : add new Test screen at MainMenu
                */
                shiftToNextFocusedScreen(gameStatus);
                break;
            case WorldShiftStart:
            case WorldShiftEnd:
                /*
                WorldShiftStart: clear the old Game ,shift not into intro but instead into an Animation
                WorldShiftEnd: clear the Animation ,shift into new game screen
                */
                shiftToNextFocusedScreen(gameStatus);
                break;
            case BackToIntro:
                /*
                return to GameIntro , used only when the current status is InGame ,Test or Setting
                that means there muss be only two Screen in ScreenStack before backToPreviewStatus();
                if less than 2 ,(which is 1) do nothing
                */

                shiftToNextFocusedScreen(gameStatus);

                break;
            case Exit:
                /* clean all saved screens */
                Gdx.app.exit();
                break;
        }

    }

    private void disposeCurrentFocusScreen() {
        FocusedScreen focusedScreen = screenStack.pop();
        statusStack.pop();
        gameFlowExecutor.setCurrentFocusedScreen(screenStack.peek());
        GameFramework.StopMonitorObject(focusedScreen.getClass().getSuperclass(), focusedScreen);
        focusedScreen.dispose();
        System.gc();
    }

    private void shiftToNextFocusedScreen(GameStatus gameStatus) {
        FocusedScreen focusedScreen = focusedScreenGenerator.generateFocusedScreen(gameStatus);
        GameFramework.setInputReceiver(focusedScreen);
        GameFramework.MonitorObject(focusedScreen.getClass().getSuperclass(), focusedScreen);
        gameFlowExecutor.setCurrentFocusedScreen(focusedScreen);
        statusStack.push(gameStatus);
        screenStack.push(focusedScreen);
    }


    public void backToPreviewStatus() {
        /*
        this will be executed  when Backspace pressed
        and there muss be at least 2 screens in the ScreenStack
        */

          /*
        *this should be executed asynchronous ,otherwise it may dispose the original caller (such as InGame)  and
        * lead to program crash
        */
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                switch (statusStack.peek()) {
                    case Intro:
                        disposeCurrentFocusScreen();
                        shiftToNextFocusedScreen(GameStatus.Intro);
                        break;
                    default:
                        disposeCurrentFocusScreen();
                        if (statusStack.peek() == GameStatus.Intro) {
                            disposeCurrentFocusScreen();
                            shiftToNextFocusedScreen(GameStatus.Intro);
                        } else {
                            shiftToNextFocusedScreen(statusStack.peek());
                        }
                }
            }
        });
    }
}
