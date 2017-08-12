package com.sunday.game.GameFramework;

import com.badlogic.gdx.Gdx;
import com.sunday.game.World.GameHub;
import com.sunday.game.World.GameIntro;
import com.sunday.game.World.GameLoading;

import java.util.Stack;

public class GameFlowManager {
    private static GameFlowManager gameFlowManager = null;
    private final Stack<FocusedScreen> ScreenStack = new Stack<FocusedScreen>();

    private GameFlowManager() {

    }

    public static final synchronized GameFlowManager getInstance() {
        if (gameFlowManager == null) {
            gameFlowManager = new GameFlowManager();
        }
        return gameFlowManager;
    }

    /**
     * Called when it needs to change status of the game
     */
    public final synchronized void setGameStatus(final GameStatus gameStatus) {
        /* this should be executed asynchronous ,otherwise it may dispose the original caller (such as InGame)  and
        * lead to program crash
        */
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
//                System.out.println("----------------------------------------------");
//                System.out.println(Thread.currentThread().toString() + " will excute GameStatus " + gameStatus.name());
                excuteGameStatus(gameStatus);
//                System.out.println("----------------------------------------------");
            }
        });
    }

    private void excuteGameStatus(GameStatus gameStatus) {
        GameHub gameHub;
        FocusedScreen focusedScreen;
        switch (gameStatus) {
            case Loading:
                /* this will be executed at first the program runs */
                gameHub = new GameHub();
                GameAdaptor.getInstance().setCurrentListener(gameHub);
                addStatus(gameStatus);
                break;
            case Intro:
                /* this will be executed  when Esc pressed */
                if (ScreenStack.empty()) {
                    addStatus(gameStatus);
                } else {
                    focusedScreen = ScreenStack.peek();
                    if (focusedScreen instanceof GameLoading) {
                        /* clear GameLoading */
                        ScreenStack.pop().dispose();
                        addStatus(gameStatus);
                        traceStack("GameLoading deleted!");
                    } else {
                        /* either only GameIntro in Stack or return to GameIntro */
                        excuteGameStatus(GameStatus.Loading.BackToIntro);
                    }
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
                addStatus(gameStatus);
                break;
            case WorldShiftStart:
            case WorldShiftEnd:
                /*
                WorldShiftStart: clear the old Game ,shift not into intro but instead into an Animation
                WorldShiftEnd: clear the Animation ,shift into new game screen
                */
                ScreenStack.pop().dispose();
                addStatus(gameStatus);
                break;
            case BackToIntro:
                /*
                return to GameIntro , used only when the current status is InGame ,Test or Setting
                that means there muss be only two Screen in ScreenStack before backToPreviewStatus();
                if less than 2 ,(which is 1) do nothing
                */
                if (ScreenStack.size() >= 2) {
                    while (ScreenStack.size() != 2)
                        ScreenStack.pop().dispose();
                    backToPreviewStatus();
                }

                break;
            case Exit:
                /* clean all saved screens */
                while ((focusedScreen = (FocusedScreen) ScreenStack.pop()) != null) {
                    focusedScreen.dispose();
                }
                Gdx.app.exit();
                break;
        }

    }

    private void addStatus(GameStatus gameStatus) {
        GameHub gameHub = (GameHub) GameAdaptor.getInstance().getCurrentListener();
        gameHub.setStatus(gameStatus);
        FocusedScreen focusedScreen = gameHub.getCurrentFocusedScreen();
        UserInputManager.getInstance().setInputReceiver(focusedScreen);
        ScreenStack.push(focusedScreen);
        traceStack("After  add Status " + gameStatus.name());
    }

    private void replaceGameIntro() {
        if (ScreenStack.size() == 1) {
            FocusedScreen focusedScreen = ScreenStack.peek();
            if (focusedScreen instanceof GameIntro) {
                /* delete old GameIntro */
                ScreenStack.pop().dispose();
                addStatus(GameStatus.Intro);
                traceStack("After replace Status GameIntro ");
            }
        }
    }

    public void backToPreviewStatus() {
        /*
        this will be executed  when Backspace pressed
        and there muss be at least 2 screens in the ScreenStack
        */

        if (ScreenStack.size() >= 2) {
            /* pop out the current screen from ScreenStack */
            FocusedScreen focusedScreen_old = ScreenStack.pop();
            /* choose stack top als the current screen */
            FocusedScreen focusedScreen = ScreenStack.peek();
            if (focusedScreen instanceof GameIntro) {
                /* this will executed when return to GameIntro */
                replaceGameIntro();
            } else {
                GameHub gameHub = (GameHub) GameAdaptor.getInstance().getCurrentListener();
                /* set stack top als the current screen */
                gameHub.setCurrentFocusedScreen(focusedScreen);
                UserInputManager.getInstance().setInputReceiver(focusedScreen);
                focusedScreen_old.dispose();
                traceStack("after backToPreviewStatus ");
            }
        }
    }

    private static void traceStack(String msg) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String classname = stackTraceElements[3].getClassName();
        String methode = stackTraceElements[3].getMethodName();
//        System.out.println(System.nanoTime() + " : " + classname + "." + methode);
//        System.out.println("Message:" + msg);
//        System.out.println(ScreenStack.toString());
    }

}
