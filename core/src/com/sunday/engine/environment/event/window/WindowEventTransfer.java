package com.sunday.engine.environment.event.window;

import com.sunday.engine.environment.event.EventTransfer;

public class WindowEventTransfer extends EventTransfer {
    private Window window;

    public WindowEventTransfer(Window window) {
        this.window = window;
    }

    public void resize(int width, int height) {
        window.width = width;
        window.height = height;
        eventDispatcher.dispatch(new WindowEvent(window, WindowSignal.Resized));
    }
}
