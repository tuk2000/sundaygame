package com.sunday.engine.rule;

import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.common.data.SystemData;
import com.sunday.engine.databank.ContextBank;

public class SystemDataContextContructor<SD extends SystemData, SDC extends SystemDataCondition> implements DataContextConstructor<SDC> {
    private ContextBank contextBank;

    public SystemDataContextContructor(ContextBank contextBank) {
        this.contextBank = contextBank;
    }

    @Override
    public boolean test(Condition condition) {
        return condition instanceof SystemDataCondition;
    }

    @Override
    public DataContext construct(SDC condition) {
        return null;
    }
}
