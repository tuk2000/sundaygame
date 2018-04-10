package com.sunday.engine.environment.driver.mouse;

import com.sunday.engine.environment.driver.DriverCondition;
import com.sunday.engine.environment.driver.DriverContext;

import java.util.function.Predicate;

public class MouseCondition extends DriverCondition<Mouse> {
    protected MouseCondition(Predicate<DriverContext<Mouse>> driverPredicate) {
        super(driverPredicate);
    }

    public static MouseCondition anyMouseSignal() {
        MouseCondition mouseCondition = new MouseCondition(context -> true);
        mouseCondition.signalCondition.setSignals(MouseSignal.class);
        return mouseCondition;
    }


    public static MouseCondition mouseDragged() {
        MouseCondition mouseCondition = new MouseCondition(context -> true);
        mouseCondition.signalCondition.setSignals(MouseSignal.Dragged);
        return mouseCondition;
    }


}
