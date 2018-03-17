package com.sunday.engine.event.driver;

import com.sunday.engine.common.Signal;
import com.sunday.engine.driver.gamepad.GamePad;

public class GamePadEvent extends DriverEvent {
    public GamePadEvent(GamePad gamePad, Signal signal) {
        super(gamePad, signal);
    }
}
