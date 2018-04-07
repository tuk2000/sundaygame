package com.sunday.engine.environment.driver.mouse;

import com.sunday.engine.environment.driver.DriverCondition;

public class MouseCondition extends DriverCondition<Mouse> {
    public static MouseCondition mouseDragged() {
        MouseCondition mouseCondition = new MouseCondition();
        mouseCondition.getSignals().add(MouseSignal.Dragged);
        return mouseCondition;
    }
}
