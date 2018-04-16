package com.sunday.engine.environment.driver.gamepad;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.environment.driver.DriverContext;

import java.util.HashMap;
import java.util.Map;

public class GamePadHub {
    private Map<Controller, GamePad> controllerGamePadMap;
    private Map<GamePad, DriverContext<GamePad>> gamePadDriverContextMap;

    public GamePadHub() {
        controllerGamePadMap = new HashMap<>();
        gamePadDriverContextMap = new HashMap<>();
    }

    public boolean hasGamePad(GamePad gamePad) {
        return gamePadDriverContextMap.containsKey(gamePad);
    }

    public void put(GamePad gamePad, DriverContext<GamePad> driverContext) {
        controllerGamePadMap.put(gamePad.controller, gamePad);
        gamePadDriverContextMap.put(gamePad, driverContext);
    }

    public DriverContext<GamePad> getGamePadDriverContext(Controller controller) {
        if (controllerGamePadMap.containsKey(controller)) {
            return gamePadDriverContextMap.get(controllerGamePadMap.get(controller));
        } else {
            return null;
        }
    }
}
