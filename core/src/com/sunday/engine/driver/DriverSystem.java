package com.sunday.engine.driver;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.databank.SubSystem;
import com.sunday.engine.databank.ports.SystemPort;
import com.sunday.engine.driver.gamepad.GamePadData;
import com.sunday.engine.driver.keyboard.KeyBoardData;
import com.sunday.engine.driver.mouse.MouseData;

public class DriverSystem extends SubSystem {

    public DriverSystem(SystemPort systemPort) {
        super("DriverSystem", systemPort);
        systemPort.addDataInstance(new KeyBoardData());
        systemPort.addDataInstance(new MouseData());
    }

    public void addDriverData(DriverData driverData) {
        systemPort.addDataInstance(driverData);
    }

    public void removeDriverData(DriverData driverData) {
        systemPort.deleteDataInstance(driverData);
    }

    public KeyBoardData getDefaultKeyBoardData() {
        return (KeyBoardData) systemPort.getDataList(e -> e.getClass().equals(KeyBoardData.class)).get(0);
    }

    public MouseData getDefaultMouseData() {
        return (MouseData) systemPort.getDataList(e -> e.getClass().equals(MouseData.class)).get(0);
    }

    public GamePadData getMatchGamePadData(Controller controller) {
        return (GamePadData) systemPort.getDataList(
                e -> e.getClass().equals(GamePadData.class)
                        & ((GamePadData) e).controller.equals(controller))
                .get(0);
    }
}
