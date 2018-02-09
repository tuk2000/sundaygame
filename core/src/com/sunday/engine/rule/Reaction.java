package com.sunday.engine.rule;

import com.sunday.engine.common.Data;

public abstract class Reaction implements Runnable, Data {
    private Condition condition;

    protected void bind(Condition condition) {
        this.condition = condition;
    }

    protected Condition getCondition() {
        return condition;
    }
}
