package com.sunday.engine.rule.condition;

import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.event.window.Window;
import com.sunday.engine.event.window.WindowSignal;

public class WindowCondition extends DataCondition<Window> {

    private WindowCondition() {
    }

    public static WindowCondition resized() {
        WindowCondition windowCondition = new WindowCondition();
        windowCondition.setSignals(WindowSignal.Resized);
        return windowCondition;
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        setData((Window) systemPort.searchInDataBank(Window.class).get(0));
        super.connectWith(systemPort);
    }
}
