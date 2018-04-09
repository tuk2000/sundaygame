package com.sunday.engine.rule;

import com.sunday.engine.common.context.SystemDataContext;

public class SystemDataCondition<SC extends SystemDataContext> extends Condition<SC> {
    protected SignalCondition<SC> scSignalCondition = new SignalCondition<>(sc -> sc.getSignal());

    @Override
    protected void generateExtraInfo() {
        setExtraInfoEntry("ConditionType", "SystemRelated");
    }

    @Override
    protected void generateMainInfo() {
        setMainInfoEntry("Source", getContext().toString());
        setMainInfoEntry("SourceClass", getContext().getClass().getCanonicalName());
        setMainInfoEntry("Signals", scSignalCondition.getSignalNames());
    }

    public void setSystemDataContext(SC systemDataContext) {
        setContext(systemDataContext);
    }

    @Override
    public boolean test(SC sc) {
        return false;
    }
}
