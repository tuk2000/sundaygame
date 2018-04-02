package com.sunday.engine.environment.event.window;

import com.sunday.engine.rule.MetaDataCondition;

public class WindowCondition extends MetaDataCondition<Window> {

    private WindowCondition() {
    }

    public static WindowCondition resized() {
        WindowCondition windowCondition = new WindowCondition();
        windowCondition.setSignals(WindowSignal.Resized);
        return windowCondition;
    }
}
