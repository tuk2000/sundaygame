package com.sunday.game.GameEntry;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.sunday.game.Graphic.GraphicPresentation;
import com.sunday.game.UserInput.UserInputManager;
import com.sunday.game.World.GameUniverse;
import com.sunday.game.World.InputReciver;
import com.sunday.game.World.Welcome;

public class GameFramework extends Gdx{

    public static GraphicPresentation graphic;
    public static GameUniverse gameUniverse;

    //fGameFramework basical  Component
    private final GameAdaptor gameAdaptor;
    private final UserInputManager userInputManager;
    private final GameFlowManager gameFlowManager;

    public GameFramework() {
        userInputManager = UserInputManager.getInstance();
        gameFlowManager = GameFlowManager.getInstance();
        gameAdaptor = GameAdaptor.getInstance();
                intialFrameWork();
    }

    private void intialFrameWork() {
        Welcome welcome=new Welcome();
        gameAdaptor.setCurrentListener(welcome);
        gameFlowManager.setGameStatus(GameFlowManager.GameStatus.Intro);
        userInputManager.setInputAdapter(welcome.getInputAdapter());
        app.getInput().setInputProcessor(userInputManager);
    }

}
