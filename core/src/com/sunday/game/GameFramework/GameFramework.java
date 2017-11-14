package com.sunday.game.GameFramework;

import com.badlogic.gdx.Gdx;
import com.sunday.game.GameFramework.GameFlow.GameFlowManager;
import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.game.GameFramework.Input.UserInputManager;
import com.sunday.game.GameFramework.Resouce.ResourceManager;
import com.sunday.game.World.GameScreenGenerator;
import com.sunday.tool.ToolApplication;

/**
 * the GameFramework consists of  GameAdaptor , UserInputManager and GameFlowManager
 * <p>
 * GameAdaptor  implements the ApplicationListener  and  it  redirect all  methods from LwjglApplication  to  a certain ApplicationListener (such as GameHub).
 * <p>
 * UserInputManager  implements the InputProcessor and it proceeds all input events to a certain InputReceiver .
 * It will be in GameFramework  appointed as default InputProcessor .
 * <p>
 * GameFlowManager  conducts the statues of the game .
 * <p>
 * TestTool contains basic information while the game is running and provides some shortcuts in oder to control the game.
 * <p>
 * When all the above components need at any place , it should be called through GameFramework , just like Gdx.
 * <p>
 */
public class GameFramework extends Gdx {

    //GameFramework basic Components
    public static UserInputManager InputProcessor;
    public static GameFlowManager GameFlow;
    public static ResourceManager Resource;
    private static ToolApplication toolApplication;

    public GameFramework() {
        app.setApplicationLogger(ToolApplication.gameLogger);
        GameScreenGenerator gameScreenGenerator = new GameScreenGenerator();

        toolApplication = new ToolApplication();
        toolApplication.runAfterInitial(() -> {
            ToolApplication.screenLoader.loadGameStatusEnum(gameScreenGenerator.enumGameStatus());
        });

        app.postRunnable(() -> {
            Thread toolLauncherThread = new Thread(toolApplication);
            toolLauncherThread.setName("ToolLaunchThread");
            toolLauncherThread.start();
        });


        Resource = new ResourceManager();
        GameFlow = new GameFlowManager(gameScreenGenerator, GameAdaptor.getInstance());
        GameFlow.setGameStatus(GameStatus.Loading);

        InputProcessor = new UserInputManager();
        input.setInputProcessor(InputProcessor);
    }

    public static void switchToolOnOrOff() {
        toolApplication.switchOnOrOff();
    }
}
