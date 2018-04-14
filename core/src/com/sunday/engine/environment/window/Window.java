package com.sunday.engine.environment.window;

import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.propertyholder.Resettable;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.environment.EnvironmentData;
import com.sunday.engine.environment.EnvironmentDataContext;

@DataMark(type = DataType.System, signalClass = {DataSignal.class, WindowSignal.class}, contextClass = EnvironmentDataContext.class)
public class Window implements EnvironmentData, Resettable, WindowRelated {
    public int width;
    public int height;

    @Override
    public void reset() {
        width = 0;
        height = 0;
    }
}