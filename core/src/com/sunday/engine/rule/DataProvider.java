package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.DataContext;

public interface DataProvider<DC extends DataCondition> {
    boolean isSuitedFor(Condition condition);

    Data requestData(DC condition);

    <D extends Data> void feedback(D data, DataContext<D> dataContext);
}
