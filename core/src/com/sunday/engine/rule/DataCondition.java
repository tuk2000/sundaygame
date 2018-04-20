package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.DataContext;

import java.util.function.Predicate;

public abstract class DataCondition<D extends Data, DC extends DataContext<D>> extends Condition<DC> {
    protected D data;
    protected SignalCondition<DC> signalCondition = new SignalCondition<>(DataContext::getSignal);
    private Predicate<DC> predicate = dc -> dc.getData().equals(data);

    public D getData() {
        return data;
    }

    protected void setPredicate(Predicate<DC> predicate) {
        this.predicate = predicate;
    }

    @Override
    protected void generateMainInfo(DC dataContext) {
        setMainInfoEntry("Source ", dataContext.getData().toString());
        setMainInfoEntry("SourceClass", dataContext.getDataClass().getSimpleName());
        setMainInfoEntry("Signals", signalCondition.getSignalNames());
    }

    @Override
    public boolean test(DC dc) {
        return signalCondition.and(predicate).test(dc);
    }
}
