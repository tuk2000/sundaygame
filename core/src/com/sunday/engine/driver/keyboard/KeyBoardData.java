package com.sunday.engine.driver.keyboard;

import com.sunday.engine.driver.DriverData;
import com.sunday.engine.driver.DriverType;

public class KeyBoardData extends DriverData {
    public KeyBoardSignal operation;
    public int key;
    public char character;

    public KeyBoardData() {
        super(DriverType.Keyboard);
    }
}
