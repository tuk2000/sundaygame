package com.sunday.game.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.sunday.game.framework.gameflow.GameFlowManager;
import com.sunday.game.framework.gameflow.GameStatus;
import com.sunday.game.framework.input.FrameworkInputProxy;
import com.sunday.game.framework.resource.ResourceManager;
import com.sunday.game.world.GameScreenGenerator;
import com.sunday.tool.ToolApplication;

import java.lang.reflect.Proxy;

/**
 * the framework consists of  GameAdaptor , FrameworkInputProxy and GameFlowManager
 * <p>
 * GameAdaptor  implements the ApplicationListener  and  it  redirect all  methods from LwjglApplication  to  a certain ApplicationListener (such as GameHub).
 * <p>
 * FrameworkInputProxy extends the function in Gdx.input , it includes a InputMultiplexer ,which has FrameworkInputProcessor and user defined InputProcessor.
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

        FrameworkInputProxy frameworkInputProxy = new FrameworkInputProxy(Gdx.input);
        Class clazz = Gdx.input.getClass();
        Input input = (Input) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), frameworkInputProxy);
        Gdx.input = input;

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
