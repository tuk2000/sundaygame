package com.sunday.engine.rule.condition;

import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.Condition;

public class CollisionCondition extends Condition {
    @Override
    protected boolean isSatisfied() {
        return false;
    }

    @Override
    protected void bindWith(SystemPort systemPort) {

    }
}
