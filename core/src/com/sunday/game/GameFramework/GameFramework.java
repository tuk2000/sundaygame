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

    public GameFramework() {
        userInputManager = UserInputManager.getInstance();
        gameFlowManager = GameFlowManager.getInstance();
        GameFlowManager.setGameStatus(GameStatus.Intro);
        app.getInput().setInputProcessor(userInputManager);
    }

}
