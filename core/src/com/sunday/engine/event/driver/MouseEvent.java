package com.sunday.engine.event.driver;

import com.sunday.engine.driver.mouse.Mouse;

public class MouseEvent extends DriverEvent {

    public MouseEvent(Mouse mouse) {
        super(mouse);
        setSignal(mouse.mouseSignal);
    }

    @Override
    public String toString() {
        Mouse mouse = (Mouse) getSource();
        return super.toString() + " [" + mouse.screenX + "," + mouse.screenY + "]";
    }
}
