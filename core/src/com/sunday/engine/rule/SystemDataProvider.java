package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.common.data.SystemData;

public class SystemDataProvider<SD extends SystemData, SDC extends SystemDataCondition> implements DataProvider<SDC> {

    @Override
    public boolean isSuitedFor(Condition condition) {
        return condition instanceof SystemDataCondition;
    }

    @Override
    public SD requestData(SDC condition) {
        return null;
    }

    @Override
    public <D extends Data> void feedback(D data, DataContext<D> dataContext) {

    }
}
