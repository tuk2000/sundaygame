package com.sunday.tool.drivermonitor;

import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.MetaDataContext;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.SystemPortSharing;
import com.sunday.engine.environment.driver.mouse.Mouse;
import com.sunday.engine.environment.driver.mouse.MouseSignal;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.tool.ToolExtender;

import java.util.function.BiConsumer;

public class MouseMonitor extends ToolExtender<MouseMonitorUIController> implements SystemPortSharing {
    private Mouse currentMouse;
    private MouseSignal currentMouseSignal = MouseSignal.None;
    private Rule mouseDataMonitorRule = new Rule(Mouse.class, DataSignal.class, new Reaction<MetaDataContext<Mouse>>() {
        @Override
        public void accept(MetaDataContext<Mouse> mouseMetaDataContext) {
            Mouse mouse = mouseMetaDataContext.getMetaData();
            DataSignal dataSignal = (DataSignal) mouseMetaDataContext.getSignal();
            switch (dataSignal) {
                case Add:
                    setCurrentMouse(mouse);
                    break;
            }
        }
    });
    private Rule mouseStatusMonitorRule = new Rule(Mouse.class, MouseSignal.class, new Reaction<MetaDataContext<Mouse>>() {
        @Override
        public void accept(MetaDataContext<Mouse> mouseMetaDataContext) {
            Mouse mouse = mouseMetaDataContext.getMetaData();
            currentMouseSignal = (MouseSignal) mouseMetaDataContext.getSignal();
            if (currentMouse != mouse)
                setCurrentMouse(mouse);
            flushBuffer();
        }
    });

    public MouseMonitor() {
    }

    public void setCurrentMouse(Mouse currentMouse) {
        if (this.currentMouse != currentMouse) {
            uiControllerBuffer.removeBuffer(this.currentMouse);
        }
        this.currentMouse = currentMouse;
        uiControllerBuffer.addBuffer(currentMouse,
                (BiConsumer<MouseMonitorUIController, Mouse>) (mouseMonitorUIController, mouse1) -> {
                    mouseMonitorUIController.setMouseSignal(currentMouseSignal.name());
                    mouseMonitorUIController.setMouseLocation(mouse1.screenX, mouse1.screenY);
                    mouseMonitorUIController.setMouseKey(Integer.toString(mouse1.key));
                });
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        systemPort.addDataInstance(mouseDataMonitorRule);
        systemPort.addDataInstance(mouseStatusMonitorRule);
    }

    @Override
    public void disconnectWith(SystemPort systemPort) {
        systemPort.removeDataInstance(mouseDataMonitorRule);
        systemPort.removeDataInstance(mouseStatusMonitorRule);
    }
}
