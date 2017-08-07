package com.sunday.game.GameFramework;

import com.badlogic.gdx.Gdx;
import com.sunday.game.World.GameHub;
import com.sunday.game.World.GameLoading;

import java.util.Stack;

public class GameFlowManager {

    private static GameFlowManager gameFlowManager = null;
    private static final Stack<FocusedScreen> ScreenStack = new Stack<FocusedScreen>();
    private static GameStatus gameStatus;

    private GameFlowManager() {
        gameStatus = GameStatus.Loading;
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
    public static final synchronized void setGameStatus(GameStatus gameStatus) {
        GameFlowManager.gameStatus = gameStatus;
        excuteGameStatus(gameStatus);
    }

    private static void excuteGameStatus(GameStatus gameStatus) {
        GameHub gameHub;
        FocusedScreen focusedScreen;
        switch (gameStatus) {
            case Loading:
                gameHub = new GameHub();
                GameAdaptor.setCurrentListener(gameHub);
                addStatus(gameStatus);
                break;
            case Intro:
                //clear GameLoading
                if(ScreenStack.peek() instanceof GameLoading){
                    System.out.println("GameLoading deleted !");
                    ScreenStack.pop();
                    //ScreenStack.pop().dispose();
                }
            case Setting:
            case InGame:
            case GamePause:
            case Test:
                //Intro : the MainMenu screen
                //Setting : add new Setting screen at MainMenu
                //InGame: add new game screen
                //GamePause: add new GamePause screen
                //Test : add new Test screen at MainMenu
                addStatus(gameStatus);
                break;
            case WorldShiftStart:
            case WorldShiftEnd:
                //WorldShiftStart: clear the old Game ,shift not into intro but instead into an Animation
                //WorldShiftEnd: clear the Animation ,shift into new game screen
                ScreenStack.pop().dispose();
                addStatus(gameStatus);
                break;
            case BackToIntro:
                //return to MainMenu , used only when the current status is InGame ,Test or Setting
                backToPreviewStatus();
                break;
            case Exit:
                //clean all saved screens
                while ((focusedScreen = (FocusedScreen) ScreenStack.pop()) != null) {
                    focusedScreen.dispose();
                }
                Gdx.app.exit();
                break;
        }

    }

    private static void addStatus(GameStatus gameStatus){
        GameHub gameHub = (GameHub) GameAdaptor.getInstance().getCurrentListener();
        gameHub.setStatus(gameStatus);
        FocusedScreen focusedScreen = gameHub.getCurrentFocusedScreen();
        UserInputManager.setInputReceiver(focusedScreen);
        ScreenStack.push(focusedScreen);
        System.out.println("After  addStatus :");
        System.out.println(ScreenStack.toString());
    }

    public static void backToPreviewStatus() {
        if(ScreenStack.size()>1){
            FocusedScreen focusedScreen_old=ScreenStack.pop();
            GameHub gameHub = (GameHub) GameAdaptor.getInstance().getCurrentListener();
            FocusedScreen  focusedScreen = ScreenStack.peek();
            gameHub.setCurrentFocusedScreen(focusedScreen);
            UserInputManager.setInputReceiver(focusedScreen);
//            focusedScreen_old.dispose();
//            focusedScreen_old.hide();
            System.out.println("After  backToPreviewStatus :");
            System.out.println(ScreenStack.toString());
        }else{
            System.out.println("there are no more preview status :");
            System.out.println(ScreenStack.toString());
        }
    }

}
