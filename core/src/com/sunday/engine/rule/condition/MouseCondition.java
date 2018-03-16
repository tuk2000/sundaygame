package com.sunday.engine.rule.condition;

import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.driver.mouse.Mouse;
import com.sunday.engine.driver.mouse.MouseSignal;

public class MouseCondition extends DataCondition<Mouse> {
    public static MouseCondition mouseDragged() {
        MouseCondition mouseCondition = new MouseCondition();
        mouseCondition.getSignals().add(MouseSignal.Draged);
        return mouseCondition;
    }

    @Override
    protected boolean isSatisfied() {
        return true;
    }

    @Override
    protected void bindWith(SystemPort systemPort) {
        setData((Mouse) systemPort.searchInDataBank(Mouse.class).get(0));
        super.bindWith(systemPort);
    }
}
