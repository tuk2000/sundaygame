package com.sunday.engine.environment.window;

import com.sunday.engine.environment.EnvironmentDataContext;

public class WindowEnvironment {
    private Window window;
    private EnvironmentDataContext<Window> windowEnvironmentDataContext;

    public WindowEnvironment() {
        window = new Window();
        windowEnvironmentDataContext = new EnvironmentDataContext<>(window);
    }

    public void resize(int width, int height) {
        window.width = width;
        window.height = height;
        windowEnvironmentDataContext.setSignal(WindowSignal.Resized);
        windowEnvironmentDataContext.evaluate();
        window.reset();
    }
}
