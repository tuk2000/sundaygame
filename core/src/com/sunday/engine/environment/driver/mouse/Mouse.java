package com.sunday.engine.environment.driver.mouse;

import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.environment.driver.Driver;
import com.sunday.engine.environment.driver.DriverContext;
import com.sunday.engine.environment.driver.DriverType;

@DataMark(type = DataType.System, signalClass = {DataSignal.class, MouseSignal.class}, contextClass = DriverContext.class)
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
