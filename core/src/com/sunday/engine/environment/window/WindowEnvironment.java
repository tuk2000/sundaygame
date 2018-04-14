package com.sunday.engine.environment.window;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.contextbank.ContextPredefining;
import com.sunday.engine.environment.EnvironmentDataContext;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.DataProvider;

public class WindowEnvironment implements DataProvider<WindowCondition> {
    private Window window;
    private EnvironmentDataContext<Window> windowEnvironmentDataContext;

    public WindowEnvironment(ContextPredefining contextPredefining) {
        window = new Window();
        windowEnvironmentDataContext = contextPredefining.getDataContext(window);
    }

    public void resize(int width, int height) {
        window.width = width;
        window.height = height;
        windowEnvironmentDataContext.setSignal(WindowSignal.Resized);
        windowEnvironmentDataContext.evaluate();
        window.reset();
    }

    @Override
    public boolean isSuitedFor(Condition condition) {
        return condition instanceof WindowCondition;
    }

    @Override
    public Window requestData(WindowCondition condition) {
        return window;
    }

    @Override
    public <D extends Data> void feedback(D data, DataContext<D> dataContext) {

    }
}
