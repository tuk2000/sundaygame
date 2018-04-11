package com.sunday.engine.rule;

import com.sunday.engine.common.context.SystemDataContext;

public class SystemDataCondition<SC extends SystemDataContext> extends Condition<SC> {
    protected SignalCondition<SC> scSignalCondition = new SignalCondition<>(sc -> sc.getSignal());

    @Override
    protected void generateExtraInfo(SC systemDataContext) {
        setExtraInfoEntry("ConditionType", "SystemDataCondition");
    }

    @Override
    protected void generateMainInfo(SC systemDataContext) {
        setMainInfoEntry("Source", systemDataContext.getSystemData().toString());
        setMainInfoEntry("SourceClass", systemDataContext.getClass().getSimpleName());
        setMainInfoEntry("Signals", scSignalCondition.getSignalNames());
    }

    @Override
    public boolean test(SC systemDataContext) {
        return false;
    }


}
