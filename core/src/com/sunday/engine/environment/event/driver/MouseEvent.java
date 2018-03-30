package com.sunday.engine.environment.event.driver;

import com.sunday.engine.common.Signal;
import com.sunday.engine.environment.driver.mouse.Mouse;

public class MouseEvent extends DriverEvent {

    public MouseEvent(Mouse mouse, Signal signal) {
        super(mouse, signal);
    }

    @Override
    public String toString() {
        Mouse mouse = (Mouse) getSource();
        return super.toString() + " [" + mouse.screenX + "," + mouse.screenY + "]";
    }
}
