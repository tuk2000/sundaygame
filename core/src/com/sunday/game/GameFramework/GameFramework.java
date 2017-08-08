package com.sunday.game.GameFramework;

import com.badlogic.gdx.Gdx;
import com.sunday.game.Graphic.GraphicPresentation;
import com.sunday.game.World.GameUniverse;

/**
 * the GameFramework consists of  GameAdaptor , UserInputManager and GameFlowManager
 * <p>
 * GameAdaptor  implements the ApplicationListener  and  it  proceeds all  methods from ApplicationListener  to  GameHub , which was defined in World package .
 * UserInputManager  implements the InputAdaptor and it proceeds all methods from InputAdaptor to a certain InputReceiver . It will be in GameFramework  appointed as default InputProcessor .
 * GameFlowManager  conducts the statues of the game .
 * <p>
 * Most of  Methods of UserInputManager and GameFlowManager are static , which means they could be called  in any places using Classname.Methode to change game status .
 */
public class GameFramework extends Gdx {

    public static GraphicPresentation graphic;
    public static GameUniverse gameUniverse;

    //GameFramework basic Component
    private final UserInputManager userInputManager;
    private final GameFlowManager gameFlowManager;

    public GameFramework() {
        userInputManager = UserInputManager.getInstance();
        gameFlowManager = GameFlowManager.getInstance();
        GameFlowManager.setGameStatus(GameStatus.Loading);
        app.getInput().setInputProcessor(userInputManager);
    }

}
