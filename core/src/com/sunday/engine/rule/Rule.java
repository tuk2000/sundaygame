package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.SystemPort;

public class Rule implements Data {
    protected Condition condition;
    protected Reaction reaction;

    public <T extends Data> Rule(Condition<T> condition, Reaction<T> reaction) {
        this.condition = condition;
        this.reaction = reaction;
        condition.setReaction(reaction);
    }

    protected void mountWith(SystemPort systemPort) {
        condition.bindWith(systemPort);
    }

    protected void dismountWith(SystemPort systemPort) {
        condition.unbindWith(systemPort);
    }
}
