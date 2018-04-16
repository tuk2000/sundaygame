package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.DataContext;

public abstract class DataCondition<D extends Data, DC extends DataContext<D>> extends Condition<DC> {
    protected D data;
    protected SignalCondition<DC> signalCondition = new SignalCondition<>(dataContext -> dataContext.getSignal());

    public D getData() {
        return data;
    }

    @Override
    protected void generateMainInfo(DC dataContext) {
        setMainInfoEntry("Source ", dataContext.getData().toString());
        setMainInfoEntry("SourceClass", dataContext.getDataClass().getSimpleName());
        setMainInfoEntry("Signals", signalCondition.getSignalNames());
    }
}
