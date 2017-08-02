package com.sunday.game.GameEntry;

public class GameFlowManager {
    public enum GameStatus {
        Loading, Intro, Setting, InGame,
    }

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
    }

    private void excuteGameStatus(GameStatus gameStatus){
        switch (GameStatus.Intro){
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
