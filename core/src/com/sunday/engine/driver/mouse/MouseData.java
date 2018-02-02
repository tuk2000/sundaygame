package com.sunday.engine.driver.mouse;

import com.sunday.engine.driver.DriverData;
import com.sunday.engine.driver.DriverType;

public class MouseData extends DriverData {
    public int screenX;
    public int screenY;

    public MouseData() {
        super(DriverType.Mouse);
    }
}
