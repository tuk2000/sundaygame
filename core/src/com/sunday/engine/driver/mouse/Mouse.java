package com.sunday.engine.driver.mouse;

import com.sunday.engine.driver.Driver;
import com.sunday.engine.driver.DriverType;

public class Mouse extends Driver {
    public MouseSignal mouseSignal;
    public int screenX;
    public int screenY;
    public int key;

    public Mouse() {
        super(DriverType.Mouse);
    }

    @Override
    public void reset() {
        mouseSignal = MouseSignal.None;
        screenX = 0;
        screenY = 0;
        key = 0;
    }
}
