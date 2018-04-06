package com.sunday.engine.environment.event.window;

import com.sunday.engine.common.MetaDataContext;
import com.sunday.engine.databank.ContextBank;
import com.sunday.engine.databank.SystemContextUser;
import com.sunday.engine.rule.MetaDataCondition;

public class WindowCondition extends MetaDataCondition<Window> implements SystemContextUser {

    private WindowCondition() {
    }

    public static WindowCondition resized() {
        WindowCondition windowCondition = new WindowCondition();
        windowCondition.setSignals(WindowSignal.Resized);
        return windowCondition;
    }

    @Override
    public void useSystemContext(ContextBank contextBank) {
        setContext((MetaDataContext<Window>) contextBank.getSystemContext("Window"));
        generateMainInfo();
    }
}
