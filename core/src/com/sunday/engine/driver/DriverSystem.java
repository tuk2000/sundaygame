package com.sunday.engine.driver;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.databank.Connection;
import com.sunday.engine.databank.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.driver.gamepad.GamePad;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.engine.driver.mouse.Mouse;

public class DriverSystem extends SubSystem {

    public DriverSystem(SystemPort systemPort) {
        super("DriverSystem", systemPort);
        systemPort.addDataInstance(new KeyBoard());
        systemPort.addDataInstance(new Mouse());
    }

    public void addDriver(Driver driver) {
        systemPort.addDataInstance(driver);
    }

    public void removeDriver(Driver driver) {
        systemPort.deleteDataInstance(driver);
    }

    public KeyBoard getDefaultKeyBoard() {
        return (KeyBoard) systemPort.getDataList(e -> e.getClass().equals(KeyBoard.class)).get(0);
    }

    public Mouse getDefaultMouse() {
        return (Mouse) systemPort.getDataList(e -> e.getClass().equals(Mouse.class)).get(0);
    }

    public GamePad getMatchGamePad(Controller controller) {
        return (GamePad) systemPort.getDataList(
                e -> e.getClass().equals(GamePad.class)
                        & ((GamePad) e).controller.equals(controller))
                .get(0);
    }
}
