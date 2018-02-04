package com.sunday.engine.driver.keyboard;

import com.sunday.engine.driver.Driver;
import com.sunday.engine.driver.DriverType;

public class KeyBoard extends Driver {
    public KeyBoardSignal keyBoardSignal;
    public int key;
    public char character;

    public KeyBoard() {
        super(DriverType.Keyboard);
    }
}
