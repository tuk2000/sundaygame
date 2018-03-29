package com.sunday.engine.driver;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.driver.gamepad.GamePad;
import com.sunday.engine.driver.gamepad.GamePadHub;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.engine.driver.mouse.Mouse;
import com.sunday.tool.ToolApplication;

import java.util.List;
import java.util.stream.Collectors;

public class DriverSystem extends SubSystem {
    private KeyBoard keyBoard = new KeyBoard();
    private Mouse mouse = new Mouse();
    private GamePadHub gamePadHub = new GamePadHub();

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
        systemPort.deleteDataInstance(driver);
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
        List<GamePad> list = (List<GamePad>) systemPort.searchInDataBank(GamePad.class).stream().filter(gamePad -> ((GamePad) gamePad).controller.equals(controller)).collect(Collectors.toList());
        return list.get(0);
    }

    public void connectToDriverMonitor() {
        ToolApplication.keyBoardMonitor.connectWith(systemPort);
        ToolApplication.mouseMonitor.connectWith(systemPort);
        ToolApplication.gamePadMonitor.connectWith(systemPort);
    }
}
