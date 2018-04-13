package com.sunday.engine.rule;

import com.sunday.engine.common.context.SystemDataContext;
import com.sunday.engine.common.data.SystemData;
import com.sunday.engine.databank.ContextBank;

public class SystemDataContextContructor<SD extends SystemData> implements DataContextConstructor<SystemDataCondition<SD>> {
    private ContextBank contextBank;

    public SystemDataContextContructor(ContextBank contextBank) {
        this.contextBank = contextBank;
    }

    @Override
    public boolean test(Condition condition) {
        return condition instanceof SystemDataCondition;
    }

    @Override
    public SystemDataContext<SD> construct(SystemDataCondition<SD> condition) {
        return null;
    }
}
