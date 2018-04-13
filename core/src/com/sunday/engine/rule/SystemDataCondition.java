package com.sunday.engine.rule;

import com.sunday.engine.common.context.SystemDataContext;
import com.sunday.engine.common.data.SystemData;

public class SystemDataCondition<SD extends SystemData> extends Condition<SystemDataContext<SD>> {
    protected SignalCondition<SystemDataContext<SD>> scSignalCondition = new SignalCondition<>(sc -> sc.getSignal());

    @Override
    protected void generateExtraInfo(SystemDataContext<SD> systemDataContext) {
        setExtraInfoEntry("ConditionType", "SystemDataCondition");
    }

    @Override
    protected void generateMainInfo(SystemDataContext<SD> systemDataContext) {
        setMainInfoEntry("Source", systemDataContext.getData().toString());
        setMainInfoEntry("SourceClass", systemDataContext.getClass().getSimpleName());
        setMainInfoEntry("Signals", scSignalCondition.getSignalNames());
    }

    @Override
    public boolean test(SystemDataContext<SD> systemDataContext) {
        return false;
    }


}
