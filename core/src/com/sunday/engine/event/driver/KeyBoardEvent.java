package com.sunday.engine.event.driver;

import com.sunday.engine.driver.keyboard.KeyBoard;

public class KeyBoardEvent extends DriverEvent {
    public KeyBoardEvent(KeyBoard driver) {
        super(driver);
        setSignal(driver.keyBoardSignal);
    }
}
