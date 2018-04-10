package com.sunday.tool.drivermonitor;

import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.SystemPortSharing;
import com.sunday.engine.environment.driver.DriverContext;
import com.sunday.engine.environment.driver.mouse.Mouse;
import com.sunday.engine.environment.driver.mouse.MouseCondition;
import com.sunday.engine.environment.driver.mouse.MouseSignal;
import com.sunday.engine.rule.ClassCondition;
import com.sunday.engine.rule.ClassReaction;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.tool.ToolExtender;

import java.util.function.BiConsumer;

public class MouseMonitor extends ToolExtender<MouseMonitorUIController> implements SystemPortSharing {
    private Mouse currentMouse;
    private MouseSignal currentMouseSignal = MouseSignal.None;
    private Rule<ClassContext<DriverContext<Mouse>>> mouseDataMonitorRule
            = new Rule<>(new ClassCondition<>(Mouse.class, DataSignal.class), new ClassReaction<DriverContext<Mouse>>() {
        @Override
        public void accept(DriverContext<Mouse> mouseDriverContext) {
            Mouse mouse = mouseDriverContext.getEnvironmentData();
            DataSignal dataSignal = (DataSignal) mouseDriverContext.getSignal();
            switch (dataSignal) {
                case Add:
                    setCurrentMouse(mouse);
                    break;
            }
        }
    });
    private Rule<DriverContext<Mouse>> mouseStatusMonitorRule
            = new Rule<>(MouseCondition.anyMouseSignal(), new Reaction<DriverContext<Mouse>>() {
        @Override
        public void accept(DriverContext<Mouse> mouseDriverContext) {
            Mouse mouse = mouseDriverContext.getEnvironmentData();
            currentMouseSignal = (MouseSignal) mouseDriverContext.getSignal();
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
