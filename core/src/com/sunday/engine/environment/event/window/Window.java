package com.sunday.engine.environment.event.window;

import com.sunday.engine.common.propertyholder.Resettable;
import com.sunday.engine.environment.EnvironmentRelatedData;
import com.sunday.engine.environment.event.WindowRelated;

public class Window implements EnvironmentRelatedData, Resettable, WindowRelated {
    public int width;
    public int height;

    @Override
    public void reset() {
        width = 0;
        height = 0;
    }
}