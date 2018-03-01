package com.sunday.tool.drivermonitor;

import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.Target;
import com.sunday.engine.driver.mouse.Mouse;
import com.sunday.tool.ToolExtender;

public class MouseMonitor extends ToolExtender<MouseMonitorUIController> implements Target {
    private Mouse mouse;

    @Override
    public void notify(Signal signal) {
        getController().setMouseSignal(signal.name());
        getController().setMouseLocation(mouse.screenX, mouse.screenY);
        getController().setMouseKey(Integer.toString(mouse.key));
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
}
