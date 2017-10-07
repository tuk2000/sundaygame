package com.sunday.game.GameFramework;

import com.badlogic.gdx.Gdx;
import com.sunday.game.GameFramework.TestTool.TestTool;

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

    //GameFramework basic Component
    public static UserInputManager userInputManager;
    public static GameFlowManager gameFlowManager;
    public static ResourceManager resourceManager;

    public GameFramework() {
        resourceManager = resourceManager.getInstance();
        gameFlowManager = GameFlowManager.getInstance();
        gameFlowManager.setGameStatus(GameStatus.Loading);
        userInputManager = UserInputManager.getInstance();
        app.getInput().setInputProcessor(userInputManager);
    }

    public static void MonitorObject(Class<?> cls, Object obj) {
        app.postRunnable(() -> {
            TestTool.MonitorObject(cls,obj);
        });
    }

    public static void StopMonitorObject(Class<?> cls, Object obj){
        app.postRunnable(() -> {
            TestTool.StopMonitorObject(cls,obj);
        });
    }
}
