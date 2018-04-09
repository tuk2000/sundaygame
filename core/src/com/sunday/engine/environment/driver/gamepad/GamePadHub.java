package com.sunday.engine.environment.driver.gamepad;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.environment.driver.Driver;
import com.sunday.engine.environment.driver.DriverContext;
import com.sunday.engine.environment.driver.DriverType;

import java.util.HashMap;
import java.util.Map;

public class GamePadHub extends Driver {
    public GamePadHub() {
        super(DriverType.GamePadHub);
    }

    private Map<Controller, DriverContext<GamePad>> map = new HashMap<>();

    public DriverContext<GamePad> getGamePadDriverContext(Controller controller) {
        if (map.containsKey(controller)) {
            return map.get(controller);
        } else {
            GamePad gamePad = new GamePad();
            gamePad.controller = controller;
            DriverContext<GamePad> gamePadDriverContext = new DriverContext<>(gamePad);
            map.put(controller, gamePadDriverContext);
            return gamePadDriverContext;
        }
    }

    @Override
    public void reset() {
        map.clear();
    }
}
