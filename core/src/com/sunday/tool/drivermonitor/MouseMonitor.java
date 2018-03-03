package com.sunday.tool.drivermonitor;

import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.Target;
import com.sunday.engine.driver.mouse.Mouse;
import com.sunday.tool.ToolExtender;

import java.util.function.BiConsumer;

public class MouseMonitor extends ToolExtender<MouseMonitorUIController> implements Target {
    private Mouse mouse;

    public MouseMonitor() {
    }

    @Override
    public void notify(Signal signal) {
        flushBuffer();
    }

    public void setMouse(Mouse mouse) {
        if (this.mouse != null) {
            uiControllerBuffer.removeBuffer(this.mouse);
        }
        this.mouse = mouse;
        uiControllerBuffer.addBuffer(mouse, new BiConsumer<MouseMonitorUIController, Mouse>() {
            @Override
            public void accept(MouseMonitorUIController mouseMonitorUIController, Mouse mouse) {
                mouseMonitorUIController.setMouseSignal(mouse.mouseSignal.name());
                mouseMonitorUIController.setMouseLocation(mouse.screenX, mouse.screenY);
                mouseMonitorUIController.setMouseKey(Integer.toString(mouse.key));
            }
        });
    }
}
