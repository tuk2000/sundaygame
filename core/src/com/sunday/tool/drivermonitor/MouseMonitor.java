package com.sunday.tool.drivermonitor;

import com.sunday.engine.common.Signal;
import com.sunday.engine.common.Target;
import com.sunday.engine.driver.mouse.Mouse;
import com.sunday.engine.driver.mouse.MouseSignal;
import com.sunday.tool.ToolExtender;

import java.util.function.BiConsumer;

public class MouseMonitor extends ToolExtender<MouseMonitorUIController> implements Target {
    private Mouse mouse;
    private MouseSignal mouseSignal = MouseSignal.None;

    public MouseMonitor() {
    }

    @Override
    public void notify(Signal signal) {
        if (signal instanceof MouseSignal) {
            mouseSignal = (MouseSignal) signal;
            flushBuffer();
        }
    }

    public void setMouse(Mouse mouse) {
        if (this.mouse != null) {
            uiControllerBuffer.removeBuffer(this.mouse);
        }
        this.mouse = mouse;
        uiControllerBuffer.addBuffer(mouse,
                (BiConsumer<MouseMonitorUIController, Mouse>) (mouseMonitorUIController, mouse1) -> {
                    mouseMonitorUIController.setMouseSignal(mouseSignal.name());
                    mouseMonitorUIController.setMouseLocation(mouse1.screenX, mouse1.screenY);
                    mouseMonitorUIController.setMouseKey(Integer.toString(mouse1.key));
                });
    }
}
