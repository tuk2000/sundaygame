package com.sunday.engine.environment.event.driver;

import com.sunday.engine.common.Signal;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;

public class KeyBoardEvent extends DriverEvent {
    public KeyBoardEvent(KeyBoard driver, Signal signal) {
        super(driver, signal);
    }
}
