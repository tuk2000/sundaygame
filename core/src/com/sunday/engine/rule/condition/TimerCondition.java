package com.sunday.engine.rule.condition;

import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.Condition;

public class TimerCondition extends Condition {
    @Override
    protected boolean isSatisfied() {
        return false;
    }

    @Override
    public void connectWith(SystemPort systemPort) {

    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }
}
