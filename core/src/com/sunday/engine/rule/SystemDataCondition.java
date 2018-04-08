package com.sunday.engine.rule;

import com.sunday.engine.common.context.SystemDataContext;

public class SystemDataCondition<SC extends SystemDataContext> extends Condition<SC> {
    @Override
    protected void generateExtraInfo() {
        setExtraInfoEntry("ConditionType", "SystemRelated");
    }

    @Override
    protected void generateMainInfo() {
        setMainInfoEntry("Source", getContext().toString());
        setMainInfoEntry("SourceClass", getContext().getClass().getCanonicalName());
        setMainInfoEntry("Signals", getSignalNames());
    }

    public void setSystemDataContext(SC systemDataContext) {
        setContext(systemDataContext);
    }
}
