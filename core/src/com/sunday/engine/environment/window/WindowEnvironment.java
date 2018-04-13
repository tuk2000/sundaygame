package com.sunday.engine.environment.window;

import com.sunday.engine.environment.EnvironmentDataContext;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.DataContextConstructor;

public class WindowEnvironment implements DataContextConstructor<WindowCondition> {
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

    @Override
    public boolean test(Condition condition) {
        return condition instanceof WindowCondition;
    }

    @Override
    public EnvironmentDataContext<Window> construct(WindowCondition windowCondition) {
        return windowEnvironmentDataContext;
    }
}
