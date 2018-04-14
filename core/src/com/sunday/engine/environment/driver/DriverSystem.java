package com.sunday.engine.environment.driver;

import com.sunday.engine.SubSystem;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.contextbank.ContextPredefining;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.environment.driver.gamepad.GamePadCondition;
import com.sunday.engine.environment.driver.gamepad.GamePadHub;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardCondition;
import com.sunday.engine.environment.driver.mouse.Mouse;
import com.sunday.engine.environment.driver.mouse.MouseCondition;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.DataProvider;
import com.sunday.tool.ToolApplication;

public class DriverSystem extends SubSystem implements DataProvider<DriverCondition<Driver>> {
    private KeyBoard keyBoard = new KeyBoard();
    private DriverContext<KeyBoard> keyBoardDriverContext;
    private Mouse mouse = new Mouse();
    private DriverContext<Mouse> mouseDriverContext;
    private GamePadHub gamePadHub = new GamePadHub();
    private DriverContext<GamePadHub> gamePadHubDriverContext;

    public DriverSystem(SystemPort systemPort, ContextPredefining contextPredefining) {
        super("DriverSystem", systemPort);
        addDriver(keyBoard);
        addDriver(mouse);
        addDriver(gamePadHub);
        keyBoardDriverContext = contextPredefining.getDataContext(keyBoard);
        mouseDriverContext = contextPredefining.getDataContext(mouse);
        gamePadHubDriverContext = contextPredefining.getDataContext(gamePadHub);
    }

    public void addDriver(Driver driver) {
        systemPort.addDataInstance(driver);
    }

    public void removeDriver(Driver driver) {
        systemPort.removeDataInstance(driver);
    }

    public DriverContext<KeyBoard> getKeyBoardDriverContext() {
        return keyBoardDriverContext;
    }

    public DriverContext<Mouse> getMouseDriverContext() {
        return mouseDriverContext;
    }

    public DriverContext<GamePadHub> getGamePadHubDriverContext() {
        return gamePadHubDriverContext;
    }


    public void connectToDriverMonitor() {
        ToolApplication.keyBoardMonitor.connectWith(systemPort);
        ToolApplication.mouseMonitor.connectWith(systemPort);
        ToolApplication.gamePadMonitor.connectWith(systemPort);
    }

    @Override
    public boolean isSuitedFor(Condition condition) {
        return condition instanceof DriverCondition;
    }

    @Override
    public Driver requestData(DriverCondition condition) {
        if (condition instanceof KeyBoardCondition) {
            return keyBoard;
        } else if (condition instanceof MouseCondition) {
            return mouse;
        } else if (condition instanceof GamePadCondition) {
            return null;
        }
        return null;
    }

    @Override
    public <D extends Data> void feedback(D data, DataContext<D> dataContext) {

    }
}
