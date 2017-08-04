package com.sunday.game.GameFramework;

import com.badlogic.gdx.Gdx;
import com.sunday.game.Graphic.GraphicPresentation;
import com.sunday.game.World.GameUniverse;

public class GameFramework extends Gdx{

    public static GraphicPresentation graphic;
    public static GameUniverse gameUniverse;


    //GameFramework basic Component
    private final UserInputManager userInputManager;
    private final GameFlowManager gameFlowManager;
    private  static GameFramework framework;

    public static void intialGameFrameWork(){
        framework=new GameFramework();
    }

    public GameFramework() {
        userInputManager = UserInputManager.getInstance();
        gameFlowManager = GameFlowManager.getInstance();
                intialFrameWork();
    }

    private void intialFrameWork() {
        gameFlowManager.setGameStatus(GameStatus.Intro);
        app.getInput().setInputProcessor(userInputManager);
    }

}
