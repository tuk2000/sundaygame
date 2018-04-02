package com.sunday.engine.environment.driver.mouse;

import com.sunday.engine.rule.DataCondition;

public class MouseCondition extends DataCondition<Mouse> {
    public static MouseCondition mouseDragged() {
        MouseCondition mouseCondition = new MouseCondition();
        mouseCondition.getSignals().add(MouseSignal.Dragged);
        return mouseCondition;
    }
}
