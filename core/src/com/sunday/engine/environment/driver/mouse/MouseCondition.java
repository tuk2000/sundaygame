package com.sunday.engine.environment.driver.mouse;

import com.sunday.engine.common.MetaDataContext;
import com.sunday.engine.databank.ContextBank;
import com.sunday.engine.databank.SystemContextUser;
import com.sunday.engine.rule.MetaDataCondition;

public class MouseCondition extends MetaDataCondition<Mouse> implements SystemContextUser {
    public static MouseCondition mouseDragged() {
        MouseCondition mouseCondition = new MouseCondition();
        mouseCondition.getSignals().add(MouseSignal.Dragged);
        return mouseCondition;
    }

    @Override
    public void useSystemContext(ContextBank contextBank) {
        setContext((MetaDataContext<Mouse>) contextBank.getSystemContext("Mouse"));
        generateMainInfo();
    }
}
