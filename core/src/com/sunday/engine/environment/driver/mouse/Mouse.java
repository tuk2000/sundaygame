package com.sunday.engine.environment.driver.mouse;

import com.sunday.engine.environment.driver.Driver;
import com.sunday.engine.environment.driver.DriverType;

public class Mouse extends Driver {
    public int screenX;
    public int screenY;
    public int key;

    public Mouse() {
        super(DriverType.Mouse);
    }

    @Override
    public void reset() {
        screenX = 0;
        screenY = 0;
        key = 0;
    }
}
