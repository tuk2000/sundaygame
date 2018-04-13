package com.sunday.engine.rule;

import com.sunday.engine.common.context.DataContext;

public interface DataContextConstructor<C extends Condition> {
    boolean test(Condition condition);

    DataContext construct(C condition);
}
