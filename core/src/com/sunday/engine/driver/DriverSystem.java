package com.sunday.engine.driver;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.databank.Connection;
import com.sunday.engine.databank.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.driver.gamepad.GamePad;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.engine.driver.mouse.Mouse;
import com.sunday.tool.ToolApplication;

public class DriverSystem extends SubSystem {
    private KeyBoard keyBoard = new KeyBoard();
    private Mouse mouse = new Mouse();

    public DriverSystem(SystemPort systemPort) {
        super("DriverSystem", systemPort);
        systemPort.addDataInstance(keyBoard);
        systemPort.addDataInstance(mouse);
        ToolApplication.keyBoardMonitor.setKeyBoard(keyBoard);
        systemPort.addDataInstance(new Connection(keyBoard, ToolApplication.keyBoardMonitor));
        ToolApplication.mouseMonitor.setMouse(mouse);
        systemPort.addDataInstance(new Connection(mouse, ToolApplication.mouseMonitor));
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

    public GamePad getMatchGamePad(Controller controller) {
        return (GamePad) systemPort.getDataList(
                e -> e.getClass().equals(GamePad.class)
                        & ((GamePad) e).controller.equals(controller))
                .get(0);
    }
}
