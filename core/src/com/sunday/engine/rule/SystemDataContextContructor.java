package com.sunday.engine.rule;

import com.sunday.engine.databank.ContextBank;

public class SystemDataContextContructor<SC extends SystemDataCondition> implements ContextConstructor<SC> {
    private ContextBank contextBank;

    public SystemDataContextContructor(ContextBank contextBank) {
        this.contextBank = contextBank;
    }

    @Override
    public boolean test(Condition condition) {
        return condition instanceof SystemDataCondition;
    }

    @Override
    public void construct(SC condition) {

    }
}
