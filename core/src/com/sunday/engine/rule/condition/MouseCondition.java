package com.sunday.engine.rule.condition;

import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.driver.mouse.Mouse;
import com.sunday.engine.driver.mouse.MouseSignal;

public class MouseCondition extends DataCondition<Mouse, MouseSignal> {
    public static MouseCondition mouseDragged() {
        MouseCondition mouseCondition = new MouseCondition();
        mouseCondition.getSignals().add(MouseSignal.Dragged);
        return mouseCondition;
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        setData((Mouse) systemPort.searchInDataBank(Mouse.class).get(0));
        super.connectWith(systemPort);
    }
}
