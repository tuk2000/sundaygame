package com.sunday.engine.environment.event.window;

import com.sunday.engine.common.MetaData;

public class Window implements MetaData {
    public int width;
    public int height;

    @Override
    public void reset() {
        width = 0;
        height = 0;
    }
}