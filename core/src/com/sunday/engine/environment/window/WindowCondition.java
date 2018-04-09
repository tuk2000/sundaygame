package com.sunday.engine.environment.window;

import com.sunday.engine.environment.EnvironmentCondition;
import com.sunday.engine.environment.EnvironmentDataContext;

public class WindowCondition extends EnvironmentCondition<Window, EnvironmentDataContext<Window>> implements WindowRelated {

    private WindowCondition() {
    }

    public static WindowCondition resized() {
        WindowCondition windowCondition = new WindowCondition();
        windowCondition.signalCondition.setSignals(WindowSignal.Resized);
        return windowCondition;
    }

    @Override
    public boolean test(EnvironmentDataContext<Window> windowEnvironmentDataContext) {
        return signalCondition.test(windowEnvironmentDataContext);
    }
}
