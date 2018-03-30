package com.sunday.engine.environment.event.window;

import com.sunday.engine.rule.DataCondition;

public class WindowCondition extends DataCondition<Window, WindowSignal> {

    private WindowCondition() {
    }

    public static WindowCondition resized() {
        WindowCondition windowCondition = new WindowCondition();
        windowCondition.setSignals(WindowSignal.Resized);
        return windowCondition;
    }
}
