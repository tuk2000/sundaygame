package com.sunday.game.GameFramework;

import com.sunday.game.World.Welcome;

public class GameFlowManager {

    private static GameFlowManager gameFlowManager = new GameFlowManager();
    private GameStatus gameStatus;

    private GameFlowManager() {
        gameStatus = GameStatus.Loading;
    }

    public static final synchronized GameFlowManager getInstance() {
        if (gameFlowManager == null) {
            gameFlowManager = new GameFlowManager();
        }
        return gameFlowManager;
    }

    public final synchronized void setGameStatus(GameStatus gameStatus) {
        this.gameStatus=gameStatus;
        excuteGameStatus(gameStatus);
    }

    private void excuteGameStatus(GameStatus gameStatus){
        Welcome welcome=new Welcome(gameStatus);
        GameAdaptor.setCurrentListener(welcome);
        UserInputManager.getInstance().setInputReciver(welcome);

        switch (gameStatus){
            case Loading:

                break;
            case Setting:
                break;
            case Intro:

                break;
            case InGame:
                break;
        }
    }

}
