package com.sunday.engine.rule;

import com.sunday.engine.common.context.DataContext;

public interface DataContextConstructor<DC extends DataCondition> {
    boolean test(Condition condition);

    DataContext construct(DC condition);
}
