package com.sunday.engine.environment.driver.keyboard;

import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.environment.driver.Driver;
import com.sunday.engine.environment.driver.DriverContext;
import com.sunday.engine.environment.driver.DriverType;

@DataMark(type = DataType.System, signalClass = {DataSignal.class, KeyBoardSignal.class}, contextClass = DriverContext.class)
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
