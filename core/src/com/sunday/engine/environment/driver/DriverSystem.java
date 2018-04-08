package com.sunday.engine.environment.driver;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.environment.driver.gamepad.GamePad;
import com.sunday.engine.environment.driver.gamepad.GamePadCondition;
import com.sunday.engine.environment.driver.gamepad.GamePadHub;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardCondition;
import com.sunday.engine.environment.driver.mouse.Mouse;
import com.sunday.engine.environment.driver.mouse.MouseCondition;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.ContextConstructor;
import com.sunday.tool.ToolApplication;

import java.util.List;
import java.util.stream.Collectors;

public class DriverSystem extends SubSystem implements ContextConstructor<DriverCondition> {
    private KeyBoard keyBoard = new KeyBoard();
    private DriverContext<KeyBoard> keyBoardDriverContext = new DriverContext<>(keyBoard);
    private Mouse mouse = new Mouse();
    private DriverContext<Mouse> mouseDriverContext = new DriverContext<>(mouse);
    private GamePadHub gamePadHub = new GamePadHub();
    private DriverContext<GamePadHub> gamePadHubDriverContext = new DriverContext<>(gamePadHub);

    public DriverSystem(SystemPort systemPort) {
        super("DriverSystem", systemPort);
        addDriver(keyBoard);
        addDriver(mouse);
        addDriver(gamePadHub);
    }

    public void addDriver(Driver driver) {
        systemPort.addDataInstance(driver);
    }

    public void removeDriver(Driver driver) {
        systemPort.removeDataInstance(driver);
    }

    public KeyBoard getDefaultKeyBoard() {
        return keyBoard;
    }

    public Mouse getDefaultMouse() {
        return mouse;
    }

    public GamePadHub getGamePadHub() {
        return gamePadHub;
    }

    public GamePad getMatchGamePad(Controller controller) {
        List<GamePad> list = systemPort.instancesOf(GamePad.class);
        list = list.stream().filter(gamePad -> gamePad.controller.equals(controller)).collect(Collectors.toList());
        return list.get(0);
    }

    public void connectToDriverMonitor() {
        ToolApplication.keyBoardMonitor.connectWith(systemPort);
        ToolApplication.mouseMonitor.connectWith(systemPort);
        ToolApplication.gamePadMonitor.connectWith(systemPort);
    }

    @Override
    public boolean accept(Condition condition) {
        return (condition instanceof KeyBoardCondition) || (condition instanceof MouseCondition) || (condition instanceof GamePadCondition);
    }

    @Override
    public void construct(DriverCondition driverCondition) {
        if (driverCondition instanceof KeyBoardCondition) {
            driverCondition.setEnvironmentContext(keyBoardDriverContext);
        } else if (driverCondition instanceof MouseCondition) {
            driverCondition.setEnvironmentContext(mouseDriverContext);
        } else if (driverCondition instanceof GamePadCondition) {
            //
        }
    }
}