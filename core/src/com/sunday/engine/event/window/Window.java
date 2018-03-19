package com.sunday.engine.event.window;

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