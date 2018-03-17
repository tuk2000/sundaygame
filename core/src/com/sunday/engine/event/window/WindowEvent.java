package com.sunday.engine.event.window;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.event.Event;

public class WindowEvent extends Event {
    private int width;
    private int height;

    public WindowEvent(Data source, Signal signal) {
        super(source, signal);
    }

    public static WindowEvent newResizeEvent(int width, int height) {
        WindowEvent windowEvent = new WindowEvent(WindowEventSource.Window, WindowSignal.Resized);
        windowEvent.width = width;
        windowEvent.height = height;
        return windowEvent;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public enum WindowEventSource implements Data {
        Window
    }
}
