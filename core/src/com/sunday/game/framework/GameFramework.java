package com.sunday.game.framework;

import com.badlogic.gdx.Gdx;
import com.sunday.game.framework.gameflow.GameFlowManager;
import com.sunday.game.framework.gameflow.GameStatus;
import com.sunday.game.framework.input.InputManager;
import com.sunday.game.framework.resource.ResourceManager;
import com.sunday.game.world.GameScreenGenerator;
import com.sunday.tool.ToolApplication;

/**
 * the framework consists of  GameAdaptor , InputManager and GameFlowManager
 * <p>
 * GameAdaptor  implements the ApplicationListener  and  it  redirect all  methods from LwjglApplication  to  a certain ApplicationListener (such as GameHub).
 * <p>
 * InputManager splits the input events to Framework and a certain InputProcessor .
 * It will be initialed in framework and guarded by GameAdaptor as default InputProcessor .
 * Use Gdx.input.setEngineInputProcessor can be automatic checked .
 * <p>
 * GameFlowManager  conducts the statues of the game .
 * <p>
 * TestTool contains basic information while the game is running and provides some shortcuts in oder to control the game.
 * <p>
 * When all the above components need at any place , it should be called through framework , just like Gdx.
 * <p>
 */
public class GameFramework {

    //framework basic Components
    public static GameFlowManager GameFlow;
    public static ResourceManager Resource;
    private static ToolApplication toolApplication;

    public GameFramework() {
        Gdx.app.setApplicationLogger(ToolApplication.gameLogger);
        GameScreenGenerator gameScreenGenerator = new GameScreenGenerator();

        InputManager inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager.getInputMultiplexer());
        GameAdaptor.getInstance().guardInputManager(inputManager);

        toolApplication = new ToolApplication();
        toolApplication.runAfterInitial(() -> {
            ToolApplication.screenLoader.loadGameStatusEnum(gameScreenGenerator.enumGameStatus());
        });

        Gdx.app.postRunnable(() -> {
            Thread toolLauncherThread = new Thread(toolApplication);
            toolLauncherThread.setName("ToolLaunchThread");
            toolLauncherThread.start();
        });


        Resource = new ResourceManager();
        GameFlow = new GameFlowManager(gameScreenGenerator, GameAdaptor.getInstance());
        GameFlow.setGameStatus(GameStatus.Loading);

    }

    public static void switchToolOnOrOff() {
        toolApplication.switchOnOrOff();
    }
}
