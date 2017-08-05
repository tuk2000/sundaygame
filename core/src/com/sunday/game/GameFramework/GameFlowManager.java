package com.sunday.game.GameFramework;

import com.badlogic.gdx.ApplicationListener;
import com.sunday.game.World.Welcome;

import java.util.Stack;

public class GameFlowManager {

    private static GameFlowManager gameFlowManager =null;
    private static final Stack<ApplicationListener> listenerStack= new Stack<ApplicationListener>();
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

    public static final synchronized void setGameStatus(GameStatus gameStatus) {
        GameFlowManager.gameStatus =gameStatus;
        excuteGameStatus(gameStatus);
    }

    private static  void excuteGameStatus(GameStatus gameStatus){
        Welcome welcome=new Welcome(gameStatus);
        GameAdaptor.setCurrentListener(welcome);
        UserInputManager.setInputReciver(welcome);

//        switch (gameStatus){
//            case Loading:
//                //listenerStack.add(welcome);
//                break;
//            case Setting:
//                break;
//            case Intro:
//                if(listenerStack.size()>1)
//                    listenerStack.pop().dispose();
//                listenerStack.push(welcome);
//                break;
//            case InGame:
//                listenerStack.push(welcome);
//                break;
//        }
//        System.out.println(listenerStack.toString());
    }

}
