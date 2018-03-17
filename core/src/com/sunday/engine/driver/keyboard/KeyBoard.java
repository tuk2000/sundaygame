package com.sunday.engine.driver.keyboard;

import com.sunday.engine.driver.Driver;
import com.sunday.engine.driver.DriverType;

public class KeyBoard extends Driver {
    public int keyCode;
    public String character;

    public KeyBoard() {
        super(DriverType.Keyboard);
        reset();
    }

    @Override
    public void reset() {
        keyCode = 0;
        character = " ";
    }
}
