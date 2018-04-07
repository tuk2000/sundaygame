package com.sunday.engine.environment.event.window;

import com.sunday.engine.environment.EnvironmentCondition;
import com.sunday.engine.environment.EnvironmentDataContext;
import com.sunday.engine.environment.event.WindowRelated;

public class WindowCondition extends EnvironmentCondition<Window, EnvironmentDataContext<Window>> implements WindowRelated {

    private WindowCondition() {
    }

    public static WindowCondition resized() {
        WindowCondition windowCondition = new WindowCondition();
        windowCondition.setSignals(WindowSignal.Resized);
        return windowCondition;
    }
}
