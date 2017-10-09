package com.sunday.game.GameFramework;

import com.badlogic.gdx.Gdx;
import com.sunday.game.GameFramework.GameFlow.GameFlowManager;
import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.game.GameFramework.Input.InputReceiver;
import com.sunday.game.GameFramework.Input.UserInputManager;
import com.sunday.game.GameFramework.Resouce.ResourceManager;
import com.sunday.game.GameFramework.TestTool.Logger.GameLogger;
import com.sunday.game.GameFramework.TestTool.ObjectMonitor.ObjectMonitor;
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

    public GameFramework() {

        app.setApplicationLogger(new GameLogger());

        GameHub gameHub = new GameHub();
        GameAdaptor.getInstance().setCurrentListener(gameHub);

        Resource = new ResourceManager();

        GameFlow = new GameFlowManager(new GameScreenGenerator(), gameHub);
        GameFlow.setGameStatus(GameStatus.Loading);

        Input = new UserInputManager();
        app.getInput().setInputProcessor(Input);

    }

    public static void MonitorObject(Class<?> clazz, Object obj) {
        app.postRunnable(() -> {
            ObjectMonitor.MonitorObject(clazz, obj);
        });
    }

    public static void StopMonitorObject(Class<?> clazz, Object obj) {
        app.postRunnable(() -> {
            ObjectMonitor.StopMonitorObject(clazz, obj);
        });
    }

}
