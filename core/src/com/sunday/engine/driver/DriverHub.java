package com.sunday.engine.driver;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.driver.gamepad.GamePadData;
import com.sunday.engine.driver.keyboard.KeyBoardData;
import com.sunday.engine.driver.mouse.MouseData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverHub {
    private Map<DriverType, List<DriverData>> connectedDrivers = new HashMap<>();

    private boolean containsDriverData(DriverData driverData) {
        return connectedDrivers.containsKey(driverData.getDriverType()) ? connectedDrivers.get(driverData).contains(driverData) : false;
    }

    private List<DriverData> getDriverList(DriverType driverType) {
        if (!connectedDrivers.containsKey(driverType)) {
            connectedDrivers.put(driverType, new ArrayList<DriverData>());
        }
        return connectedDrivers.get(driverType);
    }

    public void addDriverData(DriverData driverData) {
        if (!containsDriverData(driverData)) {
            getDriverList(driverData.getDriverType()).add(driverData);
        }
    }

    public void removeDriverData(DriverData driverData) {
        if (containsDriverData(driverData)) {
            getDriverList(driverData.getDriverType()).remove(driverData);
        }
    }

    public KeyBoardData getDefaultKeyBoardData() {
        return (KeyBoardData) getDriverList(DriverType.Keyboard).get(0);
    }

    public MouseData getDefaultMouseData() {
        return (MouseData) getDriverList(DriverType.Mouse).get(0);
    }

    public GamePadData getMatchGamePadData(Controller controller) {
        for (DriverData driverData : getDriverList(DriverType.GamePad)) {
            if (((GamePadData) driverData).controller.equals(controller)) {
                return (GamePadData) driverData;
            }
        }
        return null;
    }
}
