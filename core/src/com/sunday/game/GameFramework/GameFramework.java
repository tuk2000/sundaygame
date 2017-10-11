package com.sunday.game.GameFramework;

import com.badlogic.gdx.Gdx;
import com.sunday.game.GameFramework.GameFlow.GameFlowManager;
import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.game.GameFramework.Input.UserInputManager;
import com.sunday.game.GameFramework.Resouce.ResourceManager;
import com.sunday.game.GameFramework.TestTool.Logger.GameLogger;
import com.sunday.game.GameFramework.TestTool.ObjectMonitor.ObjectMonitor;
import com.sunday.game.GameFramework.TestTool.TestTool;
import com.sunday.game.World.GameScreenGenerator;

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
    public static UserInputManager Input;
    public static GameFlowManager GameFlow;
    public static ResourceManager Resource;
    public static TestTool Tool;

    public GameFramework() {
        app.setApplicationLogger(GameLogger.getInstance());
        app.postRunnable(() -> {
            Tool =new TestTool();

        });

        GameHub gameHub = new GameHub();
        GameAdaptor.getInstance().setCurrentListener(gameHub);

        Resource = new ResourceManager();

        GameFlow = new GameFlowManager(new GameScreenGenerator(), gameHub);
        GameFlow.setGameStatus(GameStatus.Loading);

        Input = new UserInputManager();
        app.getInput().setInputProcessor(Input);
    }

    public static void MonitorObject(Object obj) {
        app.postRunnable(() -> {
            ObjectMonitor.getInstance().MonitorObject(obj);
        });
    }

    public static void StopMonitorObject(Object obj) {
        app.postRunnable(() -> {
            ObjectMonitor.getInstance().StopMonitorObject(obj);
        });
    }

}
