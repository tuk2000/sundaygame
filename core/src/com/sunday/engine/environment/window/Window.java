package com.sunday.engine.environment.window;

import com.sunday.engine.common.propertyholder.Resettable;
import com.sunday.engine.environment.EnvironmentData;

public class Window implements EnvironmentData, Resettable, WindowRelated {
    public int width;
    public int height;

    @Override
    public void reset() {
        width = 0;
        height = 0;
    }
}