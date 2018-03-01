package com.sunday.engine.event.driver;

import com.sunday.engine.driver.gamepad.GamePad;

public class GamePadEvent extends DriverEvent {
    public GamePadEvent(GamePad gamePad) {
        super(gamePad);
        setSignal(gamePad.gamePadSignal);
    }
}
