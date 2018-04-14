package com.sunday.engine.environment.driver.gamepad;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.environment.driver.Driver;
import com.sunday.engine.environment.driver.DriverContext;
import com.sunday.engine.environment.driver.DriverType;

import java.util.HashMap;
import java.util.Map;

@DataMark(type = DataType.System, signalClass = {DataSignal.class, GamePadSignal.class}, contextClass = DriverContext.class)
public class GamePadHub extends Driver {
    private Map<Controller, DriverContext<GamePad>> map = new HashMap<>();

    public GamePadHub() {
        super(DriverType.GamePadHub);
    }

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
