package com.sunday.engine.driver;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.databank.SubSystem;
import com.sunday.engine.databank.ports.SystemPort;
import com.sunday.engine.driver.gamepad.GamePad;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.engine.driver.mouse.Mouse;

public class DriverSystem extends SubSystem {

    public DriverSystem(SystemPort systemPort) {
        super("DriverSystem", systemPort);
        systemPort.addDataInstance(new KeyBoard());
        systemPort.addDataInstance(new Mouse());
    }

    public void addDriverData(Driver driver) {
        systemPort.addDataInstance(driver);
    }

    public void removeDriverData(Driver driver) {
        systemPort.deleteDataInstance(driver);
    }

    public KeyBoard getDefaultKeyBoardData() {
        return (KeyBoard) systemPort.getDataList(e -> e.getClass().equals(KeyBoard.class)).get(0);
    }

    public Mouse getDefaultMouseData() {
        return (Mouse) systemPort.getDataList(e -> e.getClass().equals(Mouse.class)).get(0);
    }

    public GamePad getMatchGamePadData(Controller controller) {
        return (GamePad) systemPort.getDataList(
                e -> e.getClass().equals(GamePad.class)
                        & ((GamePad) e).controller.equals(controller))
                .get(0);
    }
}
